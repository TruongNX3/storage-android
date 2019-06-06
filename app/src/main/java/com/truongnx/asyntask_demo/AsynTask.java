package com.truongnx.asyntask_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AsynTask extends Activity implements View.OnClickListener {
	private TextView mTvStart;
	private TextView mTvStop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyn_task_demo);
		initView();
	}

	private void initView() {
		mTvStart = findViewById(R.id.tv_start);
		mTvStart.setOnClickListener(this);
		mTvStop = findViewById(R.id.tv_stop);
		mTvStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_start:
				break;
			case R.id.tv_stop:
				break;
			default:
		}
	}
}
