package com.reka.radiodakwahislami.Model;

import com.google.gson.annotations.SerializedName;


public class RadioModel{

	@SerializedName("averagetime")
	private int averagetime;

	@SerializedName("songtitle")
	private String songtitle;

	@SerializedName("maxlisteners")
	private int maxlisteners;

	@SerializedName("bitrate")
	private String bitrate;

	@SerializedName("streamuptime")
	private int streamuptime;

	@SerializedName("currentlisteners")
	private int currentlisteners;

	@SerializedName("version")
	private String version;

	@SerializedName("streamlistederror")
	private int streamlistederror;

	@SerializedName("content")
	private String content;

	@SerializedName("backupstatus")
	private int backupstatus;

	@SerializedName("streampath")
	private String streampath;

	@SerializedName("servergenre")
	private String servergenre;

	@SerializedName("streamhits")
	private int streamhits;

	@SerializedName("serverurl")
	private String serverurl;

	@SerializedName("servergenre2")
	private String servergenre2;

	@SerializedName("uniquelisteners")
	private int uniquelisteners;

	@SerializedName("servergenre3")
	private String servergenre3;

	@SerializedName("servertitle")
	private String servertitle;

	@SerializedName("streamlisted")
	private int streamlisted;

	@SerializedName("streamstatus")
	private int streamstatus;

	@SerializedName("peaklisteners")
	private int peaklisteners;

	@SerializedName("servergenre4")
	private String servergenre4;

	@SerializedName("servergenre5")
	private String servergenre5;

	@SerializedName("samplerate")
	private String samplerate;

	public void setAveragetime(int averagetime){
		this.averagetime = averagetime;
	}

	public int getAveragetime(){
		return averagetime;
	}

	public void setSongtitle(String songtitle){
		this.songtitle = songtitle;
	}

	public String getSongtitle(){
		return songtitle;
	}

	public void setMaxlisteners(int maxlisteners){
		this.maxlisteners = maxlisteners;
	}

	public int getMaxlisteners(){
		return maxlisteners;
	}

	public void setBitrate(String bitrate){
		this.bitrate = bitrate;
	}

	public String getBitrate(){
		return bitrate;
	}

	public void setStreamuptime(int streamuptime){
		this.streamuptime = streamuptime;
	}

	public int getStreamuptime(){
		return streamuptime;
	}

	public void setCurrentlisteners(int currentlisteners){
		this.currentlisteners = currentlisteners;
	}

	public int getCurrentlisteners(){
		return currentlisteners;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}

	public void setStreamlistederror(int streamlistederror){
		this.streamlistederror = streamlistederror;
	}

	public int getStreamlistederror(){
		return streamlistederror;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setBackupstatus(int backupstatus){
		this.backupstatus = backupstatus;
	}

	public int getBackupstatus(){
		return backupstatus;
	}

	public void setStreampath(String streampath){
		this.streampath = streampath;
	}

	public String getStreampath(){
		return streampath;
	}

	public void setServergenre(String servergenre){
		this.servergenre = servergenre;
	}

	public String getServergenre(){
		return servergenre;
	}

	public void setStreamhits(int streamhits){
		this.streamhits = streamhits;
	}

	public int getStreamhits(){
		return streamhits;
	}

	public void setServerurl(String serverurl){
		this.serverurl = serverurl;
	}

	public String getServerurl(){
		return serverurl;
	}

	public void setServergenre2(String servergenre2){
		this.servergenre2 = servergenre2;
	}

	public String getServergenre2(){
		return servergenre2;
	}

	public void setUniquelisteners(int uniquelisteners){
		this.uniquelisteners = uniquelisteners;
	}

	public int getUniquelisteners(){
		return uniquelisteners;
	}

	public void setServergenre3(String servergenre3){
		this.servergenre3 = servergenre3;
	}

	public String getServergenre3(){
		return servergenre3;
	}

	public void setServertitle(String servertitle){
		this.servertitle = servertitle;
	}

	public String getServertitle(){
		return servertitle;
	}

	public void setStreamlisted(int streamlisted){
		this.streamlisted = streamlisted;
	}

	public int getStreamlisted(){
		return streamlisted;
	}

	public void setStreamstatus(int streamstatus){
		this.streamstatus = streamstatus;
	}

	public int getStreamstatus(){
		return streamstatus;
	}

	public void setPeaklisteners(int peaklisteners){
		this.peaklisteners = peaklisteners;
	}

	public int getPeaklisteners(){
		return peaklisteners;
	}

	public void setServergenre4(String servergenre4){
		this.servergenre4 = servergenre4;
	}

	public String getServergenre4(){
		return servergenre4;
	}

	public void setServergenre5(String servergenre5){
		this.servergenre5 = servergenre5;
	}

	public String getServergenre5(){
		return servergenre5;
	}

	public void setSamplerate(String samplerate){
		this.samplerate = samplerate;
	}

	public String getSamplerate(){
		return samplerate;
	}

	@Override
 	public String toString(){
		return 
			"RadioModel{" + 
			"averagetime = '" + averagetime + '\'' + 
			",songtitle = '" + songtitle + '\'' + 
			",maxlisteners = '" + maxlisteners + '\'' + 
			",bitrate = '" + bitrate + '\'' + 
			",streamuptime = '" + streamuptime + '\'' + 
			",currentlisteners = '" + currentlisteners + '\'' + 
			",version = '" + version + '\'' + 
			",streamlistederror = '" + streamlistederror + '\'' + 
			",content = '" + content + '\'' + 
			",backupstatus = '" + backupstatus + '\'' + 
			",streampath = '" + streampath + '\'' + 
			",servergenre = '" + servergenre + '\'' + 
			",streamhits = '" + streamhits + '\'' + 
			",serverurl = '" + serverurl + '\'' + 
			",servergenre2 = '" + servergenre2 + '\'' + 
			",uniquelisteners = '" + uniquelisteners + '\'' + 
			",servergenre3 = '" + servergenre3 + '\'' + 
			",servertitle = '" + servertitle + '\'' + 
			",streamlisted = '" + streamlisted + '\'' + 
			",streamstatus = '" + streamstatus + '\'' + 
			",peaklisteners = '" + peaklisteners + '\'' + 
			",servergenre4 = '" + servergenre4 + '\'' + 
			",servergenre5 = '" + servergenre5 + '\'' + 
			",samplerate = '" + samplerate + '\'' + 
			"}";
		}
}