package com.clv.parttimejobs.thirdparty.zxing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.clv.homework.R;

public class WebViewAcitvity extends Activity{

	private TextView textview;
	private WebView webview;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ewm_webview_layout);
		Intent intent =this.getIntent();
		init();
		String text =intent.getStringExtra("value");
		if("1".equals(text)){
			String address =intent.getStringExtra("address");
			textview.setVisibility(View.GONE);
			webview.setVisibility(View.VISIBLE);
			webview.loadUrl(address);
		}else if("2".equals(text)){
			String text_content =intent.getStringExtra("text");
			textview.setVisibility(View.VISIBLE);
			webview.setVisibility(View.GONE);
			textview.setText(text_content);
		}
        
	}
	public void init(){
		textview =(TextView)findViewById(R.id.ewm_textview);
		webview =(WebView)findViewById(R.id.ewm_webiew);
	}
}
