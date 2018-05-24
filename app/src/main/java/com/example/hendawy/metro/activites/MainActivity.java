package com.example.hendawy.metro.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hendawy.metro.R;
import com.example.hendawy.metro.constants.Utils;
import com.example.hendawy.metro.helper.StationManger;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	
	TextView currentStation, targetStation,infoText,totalStationText,totalCostText,totalTimeText;
	ImageView ticketImageView;
	LinearLayout instructionLayout , infoLayout;
	StationManger stationManger;
	int currentLine = 0;
	int destinationLine = 0;
	int numberOfTransaction = 0;
	int currentStationValue = 0;
	int targetStationValue = 0;
	int totalStations = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//layout
		instructionLayout = findViewById(R.id.instruct);
		infoLayout = findViewById(R.id.infolayout);
		// textviews
		currentStation = findViewById(R.id.currentStation);
		targetStation = findViewById(R.id.targetStation);
		infoText = findViewById(R.id.infoText);
		totalStationText = findViewById(R.id.totalStations);
		totalCostText = findViewById(R.id.totalCost);
		totalTimeText = findViewById(R.id.totalTime);
		//image
		ticketImageView = findViewById(R.id.ticketImage);
		// shared preferences
		stationManger = new StationManger(getApplicationContext());

		instructionLayout.setVisibility(View.VISIBLE);
		infoLayout.setVisibility(View.GONE);
		
		if (stationManger.getUserState()) {
			currentStationValue = stationManger.getCurrentStationNumber();
			currentStation.setText(stationManger.getCurrentStationName());
		}
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			targetStationValue = extras.getInt(Utils.TARGET_STATION_NO);
			targetStation.setText(extras.getString(Utils.TARGET_STATION_NAME));
			
			stationManger.removeCurrentStationNumber();
		}
		
		if (targetStationValue > 0) {
			if (currentStationValue > 0) {
				stationManger.removeCurrentStationNumber();
				stationManger.removeCurrentStationName();
				
				currentLine = getCurrentLineNumber(currentStationValue);
				destinationLine = getDestinationLineNumber(targetStationValue);
				
				numberOfTransaction = currentLine - destinationLine;
				
				if (numberOfTransaction < 0) {
					numberOfTransaction = Math.abs(numberOfTransaction);
				}
				
				for (int i = 0; i <= numberOfTransaction; i++) {
					if (currentLine == destinationLine) {
						int totalStationsFromSameLine = targetStationValue - currentStationValue;
						if (totalStationsFromSameLine < 0) {
							totalStationsFromSameLine = Math.abs(totalStationsFromSameLine);
						}
						totalStations += totalStationsFromSameLine + 1;
					} else if (currentLine == 2 && destinationLine == 1) {
						int stationsToShohda = Utils.lineTwoStationsWeights[7] - currentStationValue; //6
						if (stationsToShohda < 0) {
							stationsToShohda = Math.abs(stationsToShohda);
						}
						int stationsToSadat = Utils.lineTwoStationsWeights[10] - currentStationValue;
						if (stationsToSadat < 0) {
							stationsToSadat = Math.abs(stationsToSadat);
						}
						if (stationsToShohda < stationsToSadat) {
							currentStationValue = Utils.lineOneStationsWeights[21];
							totalStations += stationsToShohda + 1;
						} else {
							currentStationValue = Utils.lineOneStationsWeights[18];
							totalStations += stationsToSadat + 1;
						}
						currentLine--;
					} else if (currentLine == 3 && destinationLine == 2) {
						int stationsToAtaba = Utils.lineThreeStationsWeights[8] - currentStationValue; //6
						if (stationsToAtaba < 0) {
							stationsToAtaba = Math.abs(stationsToAtaba);
						}
						totalStations = stationsToAtaba + 1;
						currentStationValue = Utils.lineTwoStationsWeights[8];
						currentLine--;
					} else if (currentLine == 1 && destinationLine == 2) {
						int stationsToShohda = Utils.lineOneStationsWeights[21] - currentStationValue; //6
						if (stationsToShohda < 0) {
							stationsToShohda = Math.abs(stationsToShohda);
						}
						int stationsToSadat = Utils.lineOneStationsWeights[18] - currentStationValue;
						if (stationsToSadat < 0) {
							stationsToSadat = Math.abs(stationsToSadat);
						}
						if (stationsToShohda < stationsToSadat) {
							currentStationValue = Utils.lineTwoStationsWeights[7];
							totalStations = stationsToShohda + 1;
						} else {
							currentStationValue = Utils.lineTwoStationsWeights[10];
							totalStations = stationsToSadat + 1;
						}
						currentLine++;
					}
					else if (currentLine == 2 && destinationLine == 3) {
						int stationsToAtaba = Utils.lineTwoStationsWeights[8] - currentStationValue; //6
						if (stationsToAtaba < 0) {
							stationsToAtaba = Math.abs(stationsToAtaba);
						}
						totalStations += stationsToAtaba + 1;
						currentStationValue = Utils.lineThreeStationsWeights[8];
						currentLine++;
					}
					else if (currentLine == 3 && destinationLine == 1) {
						int stationsToAtaba = Utils.lineThreeStationsWeights[8] - currentStationValue; //6
						if (stationsToAtaba < 0) {
							stationsToAtaba = Math.abs(stationsToAtaba);
						}
						totalStations = stationsToAtaba + 1 ;
						currentStationValue = Utils.lineTwoStationsWeights[8];
						currentLine--;
					}
					else if (currentLine == 1 && destinationLine == 3) {
						int stationsToShohda = Utils.lineOneStationsWeights[21] - currentStationValue; //6
						if (stationsToShohda < 0) {
							stationsToShohda = Math.abs(stationsToShohda);
						}
						int stationsToSadat = Utils.lineOneStationsWeights[18] - currentStationValue;
						if (stationsToSadat < 0) {
							stationsToSadat = Math.abs(stationsToSadat);
						}
						if (stationsToShohda < stationsToSadat) {
							currentStationValue = Utils.lineTwoStationsWeights[7];
							totalStations = stationsToShohda + 1;
						} else {
							currentStationValue = Utils.lineTwoStationsWeights[10];
							totalStations = stationsToSadat + 1;
						}
						currentLine++;
					}
				}
				instructionLayout.setVisibility(View.GONE);
				infoLayout.setVisibility(View.VISIBLE);
				chooseTheBestTicket(totalStations);

			}
		}
		
		
		currentStation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, StationActivity.class));
			}
		});
		
		targetStation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, StationActivity.class));
			}
		});
		
		
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}
	
	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();
		
		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {
		
		} else if (id == R.id.nav_slideshow) {
		
		} else if (id == R.id.nav_manage) {
		
		} /*else if (id == R.id.nav_share) {
		
		} else if (id == R.id.nav_send) {
		
		}*/
		
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
	
	private void chooseTheBestTicket(int totalStation) {
		
		if (totalStation < 9) {
			ticketImageView.setImageResource(R.drawable.yellow_ticket);
			infoText.setText("Your Ticket :Yellow ticket");
			totalStationText.setText(""+totalStation);
			totalCostText.setText("3.00 LE");
			totalTimeText.setText(""+totalStation * 2+"min");
		} else if (totalStation > 9 && totalStation < 17) {
			ticketImageView.setImageResource(R.drawable.green_ticket);
			infoText.setText("Your Ticket :Green ticket");
			totalStationText.setText(totalStation);
			totalCostText.setText("5.00 LE");
			totalTimeText.setText("" + totalStation * 2 + "min");
		} else {
			ticketImageView.setImageResource(R.drawable.red_ticket);
			infoText.setText("Your Ticket :Red ticket");
			totalStationText.setText(totalStation);
			totalCostText.setText("3.00 LE");
			totalTimeText.setText("" + totalStation * 2+"min");
		}
	}
	
	public int getCurrentLineNumber(int stationNumber) {
		int currentLine;
		if (stationNumber >= 1 && stationNumber <= 35) {
			currentLine = 1;
		} else if (stationNumber >= 36 && stationNumber <= 55) {
			currentLine = 2;
		} else {
			currentLine = 3;
		}
		return currentLine;
	}
	
	public int getDestinationLineNumber(int stationNumber) {
		int destinationLine;
		if (stationNumber >= 1 && stationNumber <= 35) {
			destinationLine = 1;
		} else if (stationNumber >= 36 && stationNumber <= 55) {
			destinationLine = 2;
		} else {
			destinationLine = 3;
		}
		return destinationLine;
	}
}
