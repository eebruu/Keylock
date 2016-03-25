package com.example.ebrudroid;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ImageActivity extends Activity implements PointCollectorListener {

	private PointCollector pointCollector = new PointCollector();
	private Database db = new Database(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		addTouchListener();
		showPrompt();

		pointCollector.setPointsListener(this);
	}

	private void showPrompt() {
		AlertDialog.Builder builder = new Builder(this);

		builder.setPositiveButton("Ok", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		builder.setTitle("Create your passpoint squence");
		builder.setMessage(
				"Touch four points on the image to set the passpoint sequence. You must click same points in the future the gain access to your notes.");

		AlertDialog dlg = builder.create();
		dlg.show();

	}

	private void addTouchListener() {
		ImageView image = (ImageView) findViewById(R.id.touch_image);

		image.setOnTouchListener(pointCollector);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
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

	@Override
	public void pointsCollected(List<Point> points) {
		Log.d(MainActivity.DEBUG_TAG, "Collected points: " + points.size());

		db.storePoints(points);

		List<Point> list = db.getPoints();

		for (Point point : list) {
			Log.d(MainActivity.DEBUG_TAG, String.format("get point : (%d,%d)", point.x, point.y));

		}

	}
}
