package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.gdx.Gdx;

public class Setting {
	public static boolean soundEnabled  = true;
	public static int[] hingscroes = new int[]{100, 80, 50, 30, 0};
	public final static String file = ".mysuperjumper";
	
	public static void load(){
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(Gdx.files.external(file).read()));
		try {
			soundEnabled = Boolean.parseBoolean(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void save(){
		BufferedWriter writer =null;
		writer = new BufferedWriter(new OutputStreamWriter(Gdx.files.external(file).write(false)));
		try {
			writer.write(Boolean.toString(soundEnabled));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
