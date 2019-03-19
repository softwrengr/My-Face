package com.techeasesol.myface.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.models.profileUpdateDataModel.ProfileImageResponseModel;
import com.techeasesol.myface.models.profileUpdateDataModel.ProfileUpdateResponseModel;
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


public class UpdateProfileFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.iv_edit_profile)
    ImageView ivEditProfile;
    @BindView(R.id.et_edit_name)
    EditText etName;
//    @BindView(R.id.et_edit_email)
//    EditText etEmail;
    @BindView(R.id.et_edit_number)
    EditText etNumber;
    @BindView(R.id.btn_save_profile)
    Button btnSaveProfile;


    private boolean valid = false;
    private String strName, strEmail, strNumber;
    File sourceFile;
    final int CAMERA_CAPTURE = 100;
    final int RESULT_LOAD_IMAGE = 200;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        getActivity().setTitle("Update Profile");
        BaseNetworking.grantPermission(getActivity());
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallUserProfileUpdate();
                }
            }
        });


        ivEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraBuilder();
            }
        });

    }

    private void apiCallUserProfileUpdate() {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<ProfileUpdateResponseModel> userLogin = services.updateProfile(strName, strNumber);
        userLogin.enqueue(new Callback<ProfileUpdateResponseModel>() {
            @Override
            public void onResponse(Call<ProfileUpdateResponseModel> call, Response<ProfileUpdateResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(), new ProfileFragment());
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdateResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void apiCallUpdateProfilePicture() {
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), sourceFile);
        final MultipartBody.Part profileImage = MultipartBody.Part.createFormData("profilePicture", sourceFile.getName(), requestFile);
        RequestBody Bodyname = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

        final Call<ProfileImageResponseModel> resgistration = services.updateProfilePicture(profileImage, Bodyname);
        resgistration.enqueue(new Callback<ProfileImageResponseModel>() {
            @Override
            public void onResponse(Call<ProfileImageResponseModel> call, Response<ProfileImageResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ProfileImageResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());


            }
        });
    }

    //open camera view
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
                changeProfileImage();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE && data != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            sourceFile = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
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
            ivEditProfile.setImageBitmap(thumbnail);
            changeProfileImage();

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
        ivEditProfile.setImageBitmap(BitmapFactory.decodeFile(filePath));
        return cursor.getString(column_index);

    }


    private boolean validate() {
        valid = true;

        strName = etName.getText().toString().trim();
      //  strEmail = etEmail.getText().toString().trim();
        strNumber = etNumber.getText().toString().trim();

        if (strName.isEmpty()) {
            etName.setError("enter your full name");
            valid = false;
        } else {
            etName.setError(null);
        }

//        if (strEmail.isEmpty()) {
//            etEmail.setError("enter a valid email address");
//            valid = false;
//        } else {
//            etEmail.setError(null);
//        }

        if (strNumber.isEmpty()) {
            etNumber.setError("enter a valid phone number");
            valid = false;
        } else {
            etNumber.setError(null);
        }
        return valid;
    }

    private void changeProfileImage() {
        if (sourceFile == null) {
            Toast.makeText(getActivity(), "please select your profile image", Toast.LENGTH_SHORT).show();
        } else {
            alertDialog = AlertUtils.createProgressDialog(getActivity());
            alertDialog.show();
            apiCallUpdateProfilePicture();
        }

    }
}

