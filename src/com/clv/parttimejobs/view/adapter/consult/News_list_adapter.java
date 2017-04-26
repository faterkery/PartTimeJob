package com.clv.parttimejobs.view.adapter.consult;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.News;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class News_list_adapter extends BaseAdapter {

	private Context context;
	private List<PartJobBean> news_list;
	private DisplayImageOptions options;
	private String imgurl="http://images.haidaojobs.cn/parttimePhoto/";

	public News_list_adapter(Context context, List<PartJobBean> news_list) {
		super();
		this.context = context;
		this.news_list = news_list;
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.jianzhi2) // 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.jianzhi2) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.jianzhi2) // 设置图片加载或解码过程中发生错误显示的图片
		.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
		.build(); // 创建配置过得DisplayImageOption对象
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return news_list.size();
	}

	@Override
	public View getView(int position, View viewgroup, ViewGroup partents) {
		// TODO Auto-generated method stub
		PartJobBean n = news_list.get(position);
		// 2. 将XML模板加载为程序中的模板对象(View对象)
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewgroup = inflater.inflate(R.layout.item_text, null);

		Holder holder =new Holder(viewgroup);
		// 4. 将数据现在模板的控件中
		if(n.getPhotoName().length()!=0){
			ImageLoader.getInstance().displayImage(imgurl+n.getPhotoName(),holder.image, options);
		}
		holder.tvName.setText(n.getTitle());
//		holder.tvePeopleName.setText(n.getPartTimeQualification());
//		holder.tvePay.setText(n.get());//差结算方式
		holder.tvaddress.setText(n.getLocation());
		holder.tvtime.setText(n.getWorkDate());
		holder.tvmoney.setText(n.getSalary());
		String zizhi = n.getPartTimeQualification().trim();
		if(!("000").equals(zizhi)){
		  int zz =Integer.parseInt(zizhi);
		  int z1 =zz/100;
		  int z2 =zz/10%10;
		  int z3 =zz%10;
		  switch(z1){
		  case 0:{ break;}
		  case 1:{ holder.tvimg_guan.setVisibility(View.VISIBLE);break;}
		  case 2:{ holder.tvimg_qi.setVisibility(View.VISIBLE);holder.tv_qi_kong.setVisibility(View.VISIBLE);break;}
		  }
		  switch(z2){
		  case 0:{break;}
		  case 1:{holder.tvimg_bao.setVisibility(View.VISIBLE);holder.tv_bao_kong.setVisibility(View.VISIBLE);break;}
		  case 2:{holder.tvimg_ya.setVisibility(View.VISIBLE);holder.tv_ya_kong.setVisibility(View.VISIBLE);break;}
		  }
		  switch(z3){
		  case 0:{break;}
		  case 1:{holder.tvimg_tuijian.setVisibility(View.VISIBLE);holder.tv_tuijian_kong.setVisibility(View.VISIBLE);break;}
		  }
		}else{
		}
		return viewgroup;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return news_list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	class Holder {
		ImageView image;
		TextView tvName;
		TextView tvePeopleName;
		TextView tvePay;
		TextView tvaddress;
		TextView tvtime;
		TextView tvmoney;;
		ImageView tvimg_tuijian;
		ImageView tvimg_bao;
		ImageView tvimg_qi;
		ImageView tvimg_ya;
		ImageView tvimg_guan;
		TextView tv_tuijian_kong;
		TextView tv_qi_kong;
		TextView tv_ya_kong;
		TextView tv_bao_kong;
		public Holder(View view){
			image =(ImageView) view.findViewById(R.id.shop_image);
			tvName =(TextView) view.findViewById(R.id.tv_contact_item_name);
			tvePeopleName =(TextView) view.findViewById(R.id.tv_contact_item_people);
			tvePay =(TextView) view.findViewById(R.id.tv_contact_item_pay_way);
			tvaddress =(TextView) view.findViewById(R.id.tv_address_contact);
			tvtime =(TextView) view.findViewById(R.id.tv_time);
			tvmoney =(TextView) view.findViewById(R.id.tv_money_contact);
			tvimg_tuijian=(ImageView) view.findViewById(R.id.tv_tuijian);
			tvimg_bao=(ImageView) view.findViewById(R.id.tv_bao);
			tvimg_qi=(ImageView) view.findViewById(R.id.tv_qi);
			tvimg_ya=(ImageView) view.findViewById(R.id.tv_ya);
			tvimg_guan=(ImageView) view.findViewById(R.id.tv_guan);
			tv_tuijian_kong=(TextView) view.findViewById(R.id.tv_tuijian_kong);
			tv_qi_kong=(TextView) view.findViewById(R.id.tv_qi_kong);
			tv_ya_kong=(TextView) view.findViewById(R.id.tv_ya_kong);
			tv_bao_kong=(TextView) view.findViewById(R.id.tv_bao_kong);
		}
	}
}
