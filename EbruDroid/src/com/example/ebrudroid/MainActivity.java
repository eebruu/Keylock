package com.example.ebrudroid;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String DEBUG_TAG = "JWP";
	public static final String TEXTFILE = "EbruDroid.txt";
	public static final String FILESAVED = "FileSaved";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveButtonListener();

		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		boolean fileSaved = preferences.getBoolean(FILESAVED, false);

		if (fileSaved) {
			loadSavedFile();
		}

	}

	private void loadSavedFile() {
		try {
			FileInputStream fis = openFileInput(TEXTFILE);

			EditText editText = (EditText) findViewById(R.id.text);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));

			String line;
			while ((line = buffer.readLine()) != null) {
				editText.append(line);
				editText.append("\n");
			}
		} catch (Exception e) {
			Log.d(DEBUG_TAG, "Unable to read file..");
		}
	}

	private void saveButtonListener() {
		Button saveBtn = (Button) findViewById(R.id.save);

		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText editText = (EditText) findViewById(R.id.text);

				String text = editText.getText().toString();
				try {
//					FileOutputStream fos = openFileOutput(TEXTFILE, Context.MODE_PRIVATE);
//					fos.write(text.getBytes());
//					fos.close();
//
//					SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//					SharedPreferences.Editor editor = prefs.edit();
//					editor.putBoolean(FILESAVED, true);
					
					Toast.makeText(MainActivity.this, getString(R.string.toast_cant_save), Toast.LENGTH_LONG).show();;

				} catch (Exception e) {
					Log.d(DEBUG_TAG, "Unable to save file..");
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
