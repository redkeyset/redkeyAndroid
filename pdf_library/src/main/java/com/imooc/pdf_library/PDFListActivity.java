package com.imooc.pdf_library;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Route(path = ArouterPers.PDFLibs.PDFItem)
public class PDFListActivity extends AppCompatActivity {

    private ConstraintLayout mClRoot;
    private TextView mTvTest;
    private Button mBtnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_list);
        initView();
    }

    public void generatePdf(String historyTime) {
        PdfDocument document = new PdfDocument();//1, 建立PdfDocument
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                mClRoot.getMeasuredWidth(), mClRoot.getMeasuredHeight(), 1).create();//2
        PdfDocument.Page page = document.startPage(pageInfo);
        mClRoot.draw(page.getCanvas());//3
        document.finishPage(page);

        try {

            String path = Environment.getExternalStorageDirectory() + File.separator + "table01.pdf";

            File e = new File(path);
            if (e.exists()) {
                e.delete();
            }
            document.writeTo(new FileOutputStream(e));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }

    private void initView() {
        mClRoot = (ConstraintLayout) findViewById(R.id.cl_root);
        mTvTest = (TextView) findViewById(R.id.tv_test);
        mBtnTest = (Button) findViewById(R.id.btn_test);
        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePdf("");
            }
        });
    }
}