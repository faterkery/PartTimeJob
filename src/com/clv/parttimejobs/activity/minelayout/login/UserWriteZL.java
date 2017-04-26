package com.clv.parttimejobs.activity.minelayout.login;

import com.clv.homework.R;
import com.clv.homework.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class UserWriteZL extends Activity {

	private View imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;

	private EditText editview1;
	private EditText editview2;
	private EditText editview3;

	private RadioButton radiobutton1;
	private RadioButton radiobutton2;
	
	private ImageButton submitbutton;

	String name="";
	String username="";
	String phone="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_write_zl_layout);
		imageview1=(View) findViewById(R.id.btn_circleview);
	    imageview2=(ImageView)findViewById(R.id.imageview1);
		imageview3=(ImageView)findViewById(R.id.imageview2);
		imageview4=(ImageView)findViewById(R.id.imageview3);
		
		editview1 =(EditText)findViewById(R.id.editview1);
		editview2 =(EditText)findViewById(R.id.editview2);
		editview3 =(EditText)findViewById(R.id.editview3);
		
		radiobutton1=(RadioButton) findViewById(R.id.radiobutton1);
		radiobutton2=(RadioButton) findViewById(R.id.radiobutton2);
		
		EditChangedListener e =new EditChangedListener();
		
		submitbutton=(ImageButton)findViewById(R.id.submit);
		InnerOnClickListener i =new InnerOnClickListener();
		submitbutton.setOnClickListener(i);
		
		InnerOnFocusChangeListener f =new InnerOnFocusChangeListener();
		editview1.setOnFocusChangeListener(f);
		editview2.setOnFocusChangeListener(f);
		editview3.setOnFocusChangeListener(f);
		editview1.addTextChangedListener(e);
		editview2.addTextChangedListener(e);
		editview3.addTextChangedListener(e);
	}
	private class InnerOnFocusChangeListener implements View.OnFocusChangeListener{

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.editview1:{
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
					imageview2.setImageResource(R.drawable.xxtx_bar_xinm_set);
				} else {
					// 此处为失去焦点时的处理内容
					imageview2.setImageResource(R.drawable.xxtx_bar_xinm_nor);
				}
				break;
			}
			case R.id.editview2:{
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
					imageview3.setImageResource(R.drawable.xxtx_bar_yhm_set);
				} else {
					// 此处为失去焦点时的处理内容
					imageview3.setImageResource(R.drawable.xxtx_bar_yhm_nor);
				}
				break;
			}
			case R.id.editview3:{
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
					imageview4.setImageResource(R.drawable.xxtx_bar_sjh_set);
				} else {
					// 此处为失去焦点时的处理内容
					imageview4.setImageResource(R.drawable.xxtx_bar_sjh_nor);
				}
				
				break;
			}
			}
			
		}
		
	}
	private class EditChangedListener implements TextWatcher {  
	  
	       @Override  
	       public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
	       }  
	  
	       @Override  
	       public void onTextChanged(CharSequence s, int start, int before, int count) {  
	  
	       }  
	  
	       @Override  
	       public void afterTextChanged(Editable s) {  
	    	   if(isEnd()){
					submitbutton.setImageResource(R.drawable.xxtx_bar_tijxinxi_set);
				}else{
					submitbutton.setImageResource(R.drawable.xxtx_bar_tijxinxi_nor);
				}
	       }  
	   }  
	private boolean isEnd(){
		 name =editview1.getText().toString().trim();
	     username=editview2.getText().toString().trim();
	     phone =editview3.getText().toString().trim();
	    if(!(name.equals("")||username.equals("")||phone.equals(""))){
	    	return true;
	    }
	    return false;
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.submit:{
				
				break;
			}
			}
		}
		
	}
}
