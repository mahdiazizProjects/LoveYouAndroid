package asheghetam.adapters;

import asheghetam.app.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import asheghetam.widgets.AspectImageView;

public class Main_Adapter extends BaseAdapter {

	LayoutInflater mInflater;

	int[]Gallery1= new int[] 
			{
			R.drawable.a_tumb,
			R.drawable.b_tumb,
			R.drawable.c_tumb,
			R.drawable.d_tumb,
			R.drawable.e_tumb,
			R.drawable.f_tumb,
			R.drawable.g_tumb,
			R.drawable.h_tumb,
			R.drawable.i_tumb,
			R.drawable.j_tumb,
			R.drawable.k_tumb,
			R.drawable.l_tumb,
			R.drawable.m_tumb,
			R.drawable.n_tumb,
			R.drawable.o_tumb,
			R.drawable.p_tumb,
			R.drawable.q_tumb,
			R.drawable.r_tumb,
			R.drawable.s_tumb,
			R.drawable.t_tumb,
			R.drawable.u_tumb,
			R.drawable.v_tumb,
			R.drawable.w_tumb,
			R.drawable.x_tumb,
			R.drawable.y_tumb,
			R.drawable.z_tumb,
			R.drawable.aa_tumb,
			R.drawable.bb_tumb,
			R.drawable.cc_tumb,
			R.drawable.dd_tumb,
			R.drawable.ee_tumb,
			R.drawable.ff_tumb,
			R.drawable.gg_tumb,
			R.drawable.hh_tumb,
			R.drawable.ii_tumb,
			R.drawable.jj_tumb,
			R.drawable.kk_tumb,
			R.drawable.ll_tumb,
			R.drawable.mm_tumb,
			R.drawable.nn_tumb,
			R.drawable.oo_tumb,
			R.drawable.pp_tumb,
			R.drawable.qq_tumb,
			R.drawable.rr_tumb,
			R.drawable.ss_tumb,
			R.drawable.tt_tumb
			};
	static class HoldView
	{
		AspectImageView img_tiny;
	}
	private Context context;
	public Main_Adapter(Activity context)
	{
		this.context=context;
		mInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Gallery1.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HoldView item=null;
		if(convertView==null)
		{
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			item=new HoldView();
			item.img_tiny=(AspectImageView) convertView.findViewById(R.id.image_aks);

			convertView.setTag(item);
		}
		else
			item=(HoldView) convertView.getTag();
		item.img_tiny.setImageResource(Gallery1[position]);
		return convertView;
	}

}