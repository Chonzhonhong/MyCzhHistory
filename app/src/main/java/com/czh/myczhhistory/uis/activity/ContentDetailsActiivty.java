package com.czh.myczhhistory.uis.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.czh.myczhhistory.R;
import com.czh.myczhhistory.entities.TohEntit;
import com.czh.myczhhistory.nets.CustomApiCallback;
import com.czh.myczhhistory.nets.NetService;
import com.leo.afbaselibrary.nets.exceptions.ApiException;
import com.leo.afbaselibrary.uis.activities.BaseActivity;
import com.leo.afbaselibrary.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Created By Admin  on 2020/9/28
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class ContentDetailsActiivty extends BaseActivity {
    @BindView(R.id.img_bake)
    ImageView imgBake;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_content)
    TextView tvContent;
    String id;
    @Override
    public int getContentViewId() {
        return R.layout.activity_contentdetails;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        LogUtils.e(id);
        NetService.getInstance().getResetPassword("1.0",id)
                .compose(this.bindLifeCycle())
                .subscribe(new CustomApiCallback<List<TohEntit>>() {
                    @Override
                    protected void onResultError(ApiException ex) {
                        LogUtils.e(ex.getDisplayMessage(),ex.getCode());
                        finish();
                    }

                    @Override
                    public void onNext(List<TohEntit> tohEntits) {
                        TohEntit tohEntit = tohEntits.get(0);
                        tvContent.setText(tohEntit.content);
                        tvDes.setText(tohEntit.des);
                        GlideUtils.loadImage(ContentDetailsActiivty.this, tohEntit.pic, img);
                        tvTitle.setText(tohEntit.title);
                        tvTime.setText(String.format("%s年%s月%s日    %s",tohEntit.year,tohEntit.month,tohEntit.day,tohEntit.lunar));
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_bake)
    public void onViewClicked() {
        finish();
    }
}
