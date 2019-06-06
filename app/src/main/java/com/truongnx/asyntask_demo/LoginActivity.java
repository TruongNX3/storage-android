package com.truongnx.asyntask_demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LoginActivity extends Activity implements View.OnClickListener {
	private TableRow ivCheck;
	private CheckBox mCheckBox;
	private static final int REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		initData();
	}

	private void initData() {
		ActivityCompat.requestPermissions(this,
				new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
		String rootPath= Environment.getExternalStorageDirectory().getPath();
		String path = rootPath + "/SAVE_ACC/save_acc.txt";
		String result = readFile(path);
		Log.i("log//", "Success: " + result);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if (requestCode == REQUEST_CODE) {
			if (grantResults[0] != PackageManager.GET_PERMISSIONS) {
				Toast.makeText(this, "Quyền đã được cấp!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void initView() {
		ivCheck = findViewById(R.id.tr_save_acc);
		ivCheck.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tr_save_acc:
				mCheckBox = findViewById(R.id.cb_save);
				if (mCheckBox.isSelected()) {
					mCheckBox.setChecked(false);
				} else {
					mCheckBox.setChecked(true);
				}
				saveAccountInfo();
				break;
			default:
				break;
		}
	}

	private void saveAccountInfo() {
		//vào external storage
//		String rootPath = Environment.getExternalStorageDirectory().getPath();
		String rootPath = getFilesDir().getPath();
		Log.i("log//", "" + rootPath);
		boolean result = new File(rootPath + "/SAVE_ACC").mkdirs();
		if (result) {
			Log.i("log//", "Create foder succsess!" + result);
		}
		String filePath = rootPath + "/SAVE_ACC/save_acc.txt";
		writeFile(filePath, "hello");
	}

	private String readFile(String filePath) {
		File file = new File(filePath);
		String result = "";
		if (!file.exists()) {
			Log.e("log//", "file is not exits");
			return null;
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			int len = 0;
			byte buff[] = new byte[1024];
			len = fileInputStream.read(buff);
			while (len  > 0) {
				result += new String(buff, 0, len);
				len = fileInputStream.read(buff);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private void writeFile(String filePath, String content) {
		File file = new File(filePath);
		content = "hello";
		if (file.exists()) {
			boolean result = file.delete();
			Log.i("log//", "Delete save_acc.txt!" + result);
		}
		try {
			boolean result = file.createNewFile();
			Log.i("log//", "Create new foder succsess!" + result);
			if (!result) {
				return;
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(content.getBytes());
			fileOutputStream.close();
			Log.i("log//", "Wrirte succsess!" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
