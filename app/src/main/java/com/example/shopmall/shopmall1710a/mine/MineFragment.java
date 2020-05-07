package com.example.shopmall.shopmall1710a.mine;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.view.ToorBar;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.login.view.LoginActivity;
import com.example.shopmall.shopmall1710a.mine.presenter.AdressPresenter;
import com.example.shopmall.shopmall1710a.mine.presenter.EmailPresenter;
import com.example.shopmall.shopmall1710a.mine.presenter.PhonePresenter;

import java.io.File;
import java.util.List;

public class MineFragment extends BaseFragment<Object> implements OnClickListener {
    private ImageView ibUserIconAvator;
    private android.widget.EditText phoneEdit;
    private android.widget.Button bindPhone;
    private android.widget.EditText emailEdit;
    private android.widget.Button bindEmile;
    private android.widget.EditText adressEdit;
    private android.widget.Button bindAdress;
    private PhonePresenter phonePresenter;
    private AdressPresenter adressPresenter;
    private EmailPresenter emailPresenter;
    private android.widget.TextView camera;
    private android.widget.TextView imgs;
    private Uri cameraUri;

    @Override
    protected void initData() {

    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        toorBar = (ToorBar)rootView.findViewById(R.id.toor_bar);
        ibUserIconAvator = (ImageView) rootView.findViewById(R.id.ib_user_icon_avator);

        phoneEdit = rootView.findViewById(R.id.phoneEdit);
        phoneEdit.setText(SpUtil.getPhone(getContext()));
        bindPhone = rootView.findViewById(R.id.bindPhone);
        bindPhone.setOnClickListener(this);
        emailEdit = rootView.findViewById(R.id.emailEdit);
        bindEmile = rootView.findViewById(R.id.bindEmile);
        bindEmile.setOnClickListener(this);
        adressEdit = rootView.findViewById(R.id.adressEdit);
        bindAdress = rootView.findViewById(R.id.bindAdress);
        bindAdress.setOnClickListener(this);
        camera = rootView.findViewById(R.id.camera);
        camera.setOnClickListener(this);
        imgs = rootView.findViewById(R.id.imgs);
        imgs.setOnClickListener(this);


        toorBar.setLeftImg(R.drawable.new_user_setting);
        toorBar.setRightImg(R.drawable.new_message_icon);
        toorBar.setTitle(R.string.mine_title);
        toorBar.setTextSize(R.id.ToolBarTitle,20);
        ibUserIconAvator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.minefragment;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode==1){
            adressEdit.setHint(data.toString());
            SpUtil.savePhone(getContext(),data.toString());
        }
        if (requestCode==2){
            emailEdit.setHint(data.toString());
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        }
        if (requestCode==3){
            phoneEdit.setHint(data.toString());
        }

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bindAdress:
                adressPresenter = new AdressPresenter();
                adressPresenter.attachView(this);
                adressPresenter.addParam(adressEdit.getText().toString());
                Log.e("guofeng", "onClick: " +adressEdit.getText().toString());
                adressPresenter.postHttpData(1);
                adressEdit.setText("");
                break;
            case R.id.bindEmile:
                emailPresenter = new EmailPresenter();
                emailPresenter.attachView(this);
                emailPresenter.addParam(emailEdit.getText().toString());
                Log.e("guofeng", "onClick: " +emailEdit.getText().toString());
                emailPresenter.postHttpData(2);
                emailEdit.setText("");
                break;
            case R.id.bindPhone:
                phonePresenter = new PhonePresenter();
                phonePresenter.attachView(this);
                phonePresenter.addParam(phoneEdit.getText().toString());

                phonePresenter.postHttpData(3);
                phoneEdit.setText("");
                break;
            case R.id.camera:
                Intent intent1 = new Intent();
                intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File("/sdcard/DCIM/Camera" + "/a.png");
                cameraUri = FileProvider.getUriForFile(getContext(),"com.guofeng",file);
                Toast.makeText(getContext(), ""+cameraUri, Toast.LENGTH_SHORT).show();
                intent1.putExtra(MediaStore.EXTRA_OUTPUT,cameraUri);
                startActivityForResult(intent1,102);
                break;
            case R.id.imgs:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);//PICK 选择
                intent.setType("image/*");//打开所有的图片 video/* 视频
                startActivityForResult(intent,101);
                //参数一 请求码 参数二 结果码 参数三 数据
                break;
            default:
               return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode== Activity.RESULT_OK){
            //获得数据
            Uri uri = data.getData();//图片uri
            Glide.with(getContext()).load(uri).apply(RequestOptions.circleCropTransform()).into(ibUserIconAvator);
        }
        if(requestCode==102){
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
            Glide.with(getContext()).load(cameraUri).apply(RequestOptions.circleCropTransform()).into(ibUserIconAvator);
        }
    }
}
