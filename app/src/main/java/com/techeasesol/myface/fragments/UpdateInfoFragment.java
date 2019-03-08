package com.techeasesol.myface.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.models.updateCardDataModel.AddCardResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;
import com.techeasesol.networking.BaseNetworking;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class UpdateInfoFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.iv_for_card)
    ImageView ivForCard;
    @BindView(R.id.et_card_name)
    EditText etCardName;
    @BindView(R.id.et_card_email)
    EditText etCardEmail;
    @BindView(R.id.et_card_post)
    EditText etCardPost;
    @BindView(R.id.et_card_number)
    EditText etCardnumber;
    @BindView(R.id.et_card_address)
    EditText etCardAddress;
    @BindView(R.id.et_card_facebook)
    EditText etCardFb;
    @BindView(R.id.et_card_twitter)
    EditText etCardTwitter;
    @BindView(R.id.et_card_youtube)
    EditText etCardYoutube;
    @BindView(R.id.et_card_instagram)
    EditText etCardInstagram;
    @BindView(R.id.et_card_skype)
    EditText etCardSkype;
    @BindView(R.id.et_card_in)
    EditText etCardLinkedIn;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    private String strEmail, strName, strPost, strNumber, strAddress, strFB = "", strYoutube = "",
            strLinkedin = "", strInstagram = "", strSkype = "", strTwitter = "";

    File sourceFile, optionalFile;
    private String token;
    int cardID;
    final int CAMERA_CAPTURE = 10;
    final int RESULT_LOAD_IMAGE = 20;
    boolean valid = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_info, container, false);
        getActivity().setTitle("Editing");
        BaseNetworking.grantPermission(getActivity());
        token = GeneralUtils.getApiToken(getActivity());
        cardID = GeneralUtils.getCardID(getActivity());

        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);

        ivForCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraBuilder();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallUpdateCardInfo();
                }


            }
        });
    }


    public void cameraBuilder() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Open");
        String[] pictureDialogItems = {
                "\tGallery",
                "\tCamera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                galleryIntent();

                                break;
                            case 1:
                                cameraIntent();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void cameraIntent() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(captureIntent, CAMERA_CAPTURE);
    }

    public void galleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    private void apiCallUpdateCardInfo() {
        ApiInterface services = ApiClient.getApiClient(token).create(ApiInterface.class);
        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), sourceFile);
        final MultipartBody.Part profileImage = MultipartBody.Part.createFormData("picture", sourceFile.getName(), requestFile);
        RequestBody Bodyname = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
        RequestBody nameBody = RequestBody.create(MediaType.parse("multipart/form-data"), strName);
        RequestBody numberlBody = RequestBody.create(MediaType.parse("multipart/form-data"), strNumber);
        RequestBody emailBody = RequestBody.create(MediaType.parse("multipart/form-data"), strEmail);
        RequestBody postBody = RequestBody.create(MediaType.parse("multipart/form-data"), strPost);
        RequestBody addresstBody = RequestBody.create(MediaType.parse("multipart/form-data"), strAddress);
        RequestBody fbBody = RequestBody.create(MediaType.parse("multipart/form-data"), strFB);
        RequestBody twitterBody = RequestBody.create(MediaType.parse("multipart/form-data"), strTwitter);
        RequestBody instagramBody = RequestBody.create(MediaType.parse("multipart/form-data"), strInstagram);
        RequestBody linkedinBody = RequestBody.create(MediaType.parse("multipart/form-data"), strLinkedin);
        RequestBody skypeBody = RequestBody.create(MediaType.parse("multipart/form-data"), strSkype);
        RequestBody youtubeBody = RequestBody.create(MediaType.parse("multipart/form-data"), strYoutube);

        final Call<AddCardResponseModel> resgistration = services.updateCard(cardID, nameBody, numberlBody, emailBody, postBody, addresstBody, fbBody, twitterBody, instagramBody, linkedinBody, skypeBody, youtubeBody, profileImage, Bodyname);
        resgistration.enqueue(new Callback<AddCardResponseModel>() {
            @Override
            public void onResponse(Call<AddCardResponseModel> call, Response<AddCardResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    GeneralUtils.connectDrawerFragment(getActivity(), new YourCardFragment());
                }

            }

            @Override
            public void onFailure(Call<AddCardResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());


            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImageUri = data.getData();
            String imagepath = getPath(selectedImageUri);
            sourceFile = new File(imagepath);
            try {
                sourceFile = new Compressor(getActivity()).compressToFile(sourceFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE && data != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            thumbnail.compress(Bitmap.CompressFormat.PNG, 90, bytes);
            sourceFile = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".png");
            FileOutputStream fo;
            try {
                sourceFile.createNewFile();
                fo = new FileOutputStream(sourceFile);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ivForCard.setImageBitmap(thumbnail);

        }
    }

    @SuppressLint("SetTextI18n")
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        Log.d("path", filePath);
        ivForCard.setImageBitmap(BitmapFactory.decodeFile(filePath));
        return cursor.getString(column_index);

    }


    private boolean validate() {
        valid = true;
        strEmail = etCardEmail.getText().toString().trim();
        strName = etCardName.getText().toString().trim();
        strPost = etCardPost.getText().toString().trim();
        strNumber = etCardName.getText().toString().trim();
        strAddress = etCardAddress.getText().toString().trim();
        strFB = etCardFb.getText().toString().trim();
        strTwitter = etCardTwitter.getText().toString().trim();
        strYoutube = etCardYoutube.getText().toString().trim();
        strInstagram = etCardInstagram.getText().toString().trim();
        strSkype = etCardSkype.getText().toString().trim();
        strLinkedin = etCardLinkedIn.getText().toString().trim();

        if (sourceFile == null || sourceFile.equals("")) {
            Toast.makeText(getActivity(), "please select image", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {

        }

        if (strName.isEmpty()) {
            etCardName.setError("Please enter name");
            valid = false;
        } else {
            etCardName.setError(null);
        }

        if (strPost.isEmpty()) {
            etCardPost.setError("Please enter name");
            valid = false;
        } else {
            etCardPost.setError(null);
        }

        if (strEmail.isEmpty()) {
            etCardEmail.setError("enter email address");
            valid = false;
        } else {
            etCardEmail.setError(null);
        }


        if (strNumber.isEmpty()) {
            etCardName.setError("Please enter name");
            valid = false;
        } else {
            etCardName.setError(null);
        }

        if (strAddress.isEmpty()) {
            etCardAddress.setError("Please enter name");
            valid = false;
        } else {
            etCardAddress.setError(null);
        }

        return valid;
    }

}
