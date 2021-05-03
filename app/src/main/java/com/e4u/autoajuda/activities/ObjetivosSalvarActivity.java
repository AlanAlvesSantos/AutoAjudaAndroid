package com.e4u.autoajuda.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.e4u.autoajuda.R;
import com.e4u.autoajuda.modelos.ItemListaModel;
import com.e4u.autoajuda.repositorio.ItemListaRepository;
import com.e4u.autoajuda.utilidades.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ObjetivosSalvarActivity extends AppCompatActivity {

    EditText edtMeta;
    ImageView imgAddImage;
    TextView txtAddImage;
    Button btnGravarMeta, btnDeletar;

    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    public static final int GALLERY = 1;
    public static final int CAMERA = 2;
    public static final String IMAGE_DIRECTORY = "/AutoAjudaApp/";
    String imagemPath = "";
    Context context;
    private int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos_salvar);
        context = this;

        id = getIntent().getIntExtra("id", 0);

        vincularComponentsXML();
        checkPermissions();
    }

    private void vincularComponentsXML() {
        edtMeta = findViewById(R.id.edtMeta);
        imgAddImage = findViewById(R.id.imgAddImage);
        txtAddImage = findViewById(R.id.txtAddImage);
        btnGravarMeta = findViewById(R.id.btnGravarMeta);
        btnDeletar = findViewById(R.id.btnDeletar);

        txtAddImage.setOnClickListener(v -> showPictureDialog());

        btnGravarMeta.setOnClickListener(v -> salvarMeta());

        btnDeletar.setOnClickListener(v -> deletarMeta());


        if (id > 0) {
            ItemListaRepository repository = new ItemListaRepository(context);

            ItemListaModel item = repository.getItemByID(id);

            btnDeletar.setVisibility(View.VISIBLE);

            edtMeta.setText(item.getItemDescription());
            imagemPath = item.getItemImagem();

            if (!item.getItemImagem().equals(""))
                Glide.with(context).load(item.getItemImagem()).into(imgAddImage);

        }
    }

    private void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            return;
        }
    }

    private void salvarMeta() {

        ItemListaModel item = new ItemListaModel();
        item.setItemDescription(edtMeta.getText().toString());
        item.setItemImagem(imagemPath);
        item.setItemType(Utils.METAS);

        if (item.getItemDescription().equals("")) {
            Toast.makeText(context, getString(R.string.message_error_meta), Toast.LENGTH_LONG).show();
        } else {

            ItemListaRepository repository = new ItemListaRepository(context);

            if (id == 0) {
                repository.insertOne(item);
                Toast.makeText(context, getString(R.string.meta_salva_sucesso), Toast.LENGTH_LONG).show();
            } else {
                item.setItemID(id);
                repository.updateOne(item);
                Toast.makeText(context, getString(R.string.meta_alterada_sucesso), Toast.LENGTH_LONG).show();
            }

            finish();
        }
    }

    private void deletarMeta() {
        ItemListaRepository repository = new ItemListaRepository(context);
        ItemListaModel item = new ItemListaModel();
        item.setItemID(id);
        item.setItemImagem(imagemPath);
        item.setItemDescription(edtMeta.getText().toString());
        item.setItemType(Utils.METAS);

        repository.deleteOne(item);
        Toast.makeText(context, getString(R.string.meta_excluida_sucesso), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    saveImage(bitmap);

                    imgAddImage.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgAddImage.setImageBitmap(thumbnail);
            saveImage(thumbnail);
        }
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle(getString(R.string.selecionar));
        String[] pictureDialogItems = {
                getString(R.string.galeria),
                getString(R.string.camera)};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {

        try {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, GALLERY);
        } catch (Exception ex) {
            Toast.makeText(context, getString(R.string.erro_permissao_galeria), Toast.LENGTH_LONG).show();
        }
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        File wallpaperDirectory = new File(
                getApplicationContext().getFilesDir().getPath() + IMAGE_DIRECTORY);

        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            imagemPath = f.getAbsolutePath();
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return "";
    }
}