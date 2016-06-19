package children.me;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.ygh.falltestprogram.R;

import loading.BaseApplication;
import loading.Loading;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
@SuppressLint("InflateParams")
public class LogoutDialogFragment extends DialogFragment implements
		OnClickListener {
	public static final String action = "ygh.broadcast.action";
	private Activity mActivity;
	private View btnLogout;
	private View divider;
	SharedPreferences pref;

	/** 登出操作对应的listener */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mActivity = getActivity();
		View inflate = inflater.inflate(R.layout.fragment_dialog_logout, null);
		inflate.findViewById(R.id.tv_exit).setOnClickListener(this);
		divider = inflate.findViewById(R.id.divider);
		btnLogout = inflate.findViewById(R.id.tv_logout);
		btnLogout.setOnClickListener(this);
		initLogin();
		getDialog().getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		return inflate;
	}

	private void initLogin() {
		// 读取登录类型
		pref = mActivity.getSharedPreferences("son_user", Context.MODE_PRIVATE);
		boolean type = pref.getBoolean("islogin", false);
		if (type == false) {
			// 未登录，只显示退出客户端按钮
			btnLogout.setVisibility(View.GONE);
			divider.setVisibility(View.GONE);
		} else {

		}
	}

	@Override
	public void onClick(View v) {
		dismiss();
		switch (v.getId()) {
			case R.id.tv_exit: // 退出应用
				BaseApplication.getInstance().exit();
				break;
			case R.id.tv_logout: // 注销
				logout();
				break;

			default:
				break;
		}
	}

	private void logout() {
		anounceLogout();
		dismiss();
		Intent intent = new Intent(mActivity, Loading.class);
		startActivity(intent);
		BaseApplication.getInstance().exit();
		Toast.makeText(mActivity, "已注销", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 发送广播注销
	 */
	private void anounceLogout() {
		Editor edit = pref.edit();
		edit.putBoolean("islogin", false);
		edit.commit();
	}

}
