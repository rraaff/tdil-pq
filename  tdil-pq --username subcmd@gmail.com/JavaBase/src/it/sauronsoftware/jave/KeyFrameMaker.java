package it.sauronsoftware.jave;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KeyFrameMaker {
	
	private ArrayList<String> parameters;
	
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void execute() throws Exception{
		Runtime r = Runtime.getRuntime();
		if(this.parameters == null){
			throw new Exception("flvtool2 parameters not setted");
		}
		parameters.add(this.getFileName());
		String[] array = new String[parameters.size()];
		for(int i=0;i<parameters.size(); i++){
			array[i] = parameters.get(i);
		}
		Process process = r.exec(array);
		if(process.getErrorStream()!=null){
			
			InputStreamReader io = new InputStreamReader(process.getErrorStream());
			BufferedReader reader = new BufferedReader(io);
			String line = reader.readLine();
			if(line != null && line.trim().length() > 0){
				throw new Exception("flvtool2 error, filename:" +this.getFileName());
			}
		}
		
	
	}
	
	public void generateAudioParameters(){
		this.generateCommonParameters();
		//Parameter to make keyframe for audio
		this.parameters.add("-y");
	}
	
	public void generateVideoParameters(){
		this.generateCommonParameters();
	}
	
	private void generateCommonParameters(){
		if(this.parameters == null){
			this.parameters = new ArrayList<String>();
		}
		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
			this.parameters.add("nice");
			this.parameters.add("-n");
			this.parameters.add("10");
		}
		this.parameters.add("flvtool2");
		this.parameters.add("-U");
	}
}
