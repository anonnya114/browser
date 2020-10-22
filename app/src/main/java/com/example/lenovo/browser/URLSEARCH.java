package com.example.lenovo.browser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class URLSEARCH extends AppCompatActivity implements View.OnClickListener {


    private Button searchB;
    private EditText urlsearch;
    private WebView webView;
    private Button back;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlsearch);

        searchB=(Button)findViewById(R.id.button);
        urlsearch=(EditText) findViewById(R.id.editText);
        webView=(WebView)findViewById(R.id.web);

        url=getIntent().getExtras().get("url_search").toString();
        urlsearch.setText(url);


        searchB.setOnClickListener(this);
        back.setOnClickListener(this);



        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }



    @Override
    public void onClick(View v) {

        if(v==searchB)
        {
            searchwebadd();

        }

    }

    private void searchwebadd() {

        String url_search=searchB.getText().toString();
        if(TextUtils.isEmpty(url_search))
        {
            Toast empty=Toast.makeText(URLSEARCH.this,"Please,Enter Url or website",Toast.LENGTH_LONG);
            empty.show();
        }
        else
        {
            String url_without_http=url_search.replaceAll("https://www.","");
            String https="https://";
            String www="www.";
            Intent searchInt=new Intent(URLSEARCH.this,URLSEARCH.class);
            searchInt.putExtra("url_search",https+www+url_without_http);
            startActivity(searchInt);

            searchB.setText("");
            searchB.requestFocus();
        }
    }
}
