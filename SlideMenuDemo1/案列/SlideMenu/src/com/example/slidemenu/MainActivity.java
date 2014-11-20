package com.example.slidemenu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity {
	SlidingMenu menu;
	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initSlidingMenu();
		initRightMenu();
		initLeftMenu();	
	}

	private void initLeftMenu() {
		menu.setMenu(R.layout.main_left_layout);
		FragmentTransaction leftTransaction = manager.beginTransaction();
		leftTransaction.replace(R.id.leftmenu, new Left_Fragment()).commit();
		
	}

	private void initRightMenu() {
		menu.setSecondaryMenu(R.layout.main_right_layout);
		FragmentTransaction rightTransaction = manager.beginTransaction();
		rightTransaction.replace(R.id.rightmenu, new Right_Fragment()).commit();
	}

	private void initSlidingMenu() {
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// ��������ģʽ
		menu.setBehindOffset(200);// �򿪲˵������µ��������
		menu.setFadeDegree(0.35f);// ���뵭��ı���
		menu.setFadeEnabled(true);// �����Ƿ��н���
		menu.setBehindScrollScale(0.133f);// ���ý�����
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		manager = getSupportFragmentManager();
	}

	public void onclick(View view) {

		switch (view.getId()) {
		case R.id.Button_left:
			menu.showMenu(true);
			break;
		case R.id.button_right:
			menu.showSecondaryMenu(true);
			break;
		}
	}
}
