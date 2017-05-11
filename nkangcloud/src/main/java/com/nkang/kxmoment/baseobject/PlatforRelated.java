package com.nkang.kxmoment.baseobject;

import java.util.List;

/*
 * for AGM_Backlog
 */
public class PlatforRelated {
	int Done_APJ;
	int Done_USA;
	int Done_MEXICO;
	int Done_EMEA;
	
	int InProgress_APJ;
	int InProgress_USA;
	int InProgress_MEXICO;
	int InProgress_EMEA;

	int InPlanning_APJ;
	int InPlanning_USA;
	int InPlanning_MEXICO;
	int InPlanning_EMEA;
	
	int unAssinged;
	int IMMetricstotal;
	int RunMaintainMetricstotal;
	
	int Closed_APJ;
	int Closed_USA;
	int Closed_MEXICO;
	int Closed_EMEA;
	int Closed_OTHER;
	
	List<String> outNames;
	
	public List<String> getOutNames() {
		return outNames;
	}

	public void setOutNames(List<String> outNames) {
		this.outNames = outNames;
	}

	public int getClosed_OTHER() {
		return Closed_OTHER;
	}

	public void setClosed_OTHER(int closed_OTHER) {
		Closed_OTHER = closed_OTHER;
	}

	public int getClosed_APJ() {
		return Closed_APJ;
	}

	public void setClosed_APJ(int closed_APJ) {
		Closed_APJ = closed_APJ;
	}

	public int getClosed_USA() {
		return Closed_USA;
	}

	public void setClosed_USA(int closed_USA) {
		Closed_USA = closed_USA;
	}

	public int getClosed_MEXICO() {
		return Closed_MEXICO;
	}

	public void setClosed_MEXICO(int closed_MEXICO) {
		Closed_MEXICO = closed_MEXICO;
	}

	public int getClosed_EMEA() {
		return Closed_EMEA;
	}

	public void setClosed_EMEA(int closed_EMEA) {
		Closed_EMEA = closed_EMEA;
	}

	public int getDone_APJ() {
		return Done_APJ;
	}


	public int getIMMetricstotal() {
		return IMMetricstotal;
	}

	public void setIMMetricstotal(int iMMetricstotal) {
		IMMetricstotal = iMMetricstotal;
	}

	public int getRunMaintainMetricstotal() {
		return RunMaintainMetricstotal;
	}

	public void setRunMaintainMetricstotal(int runMaintainMetricstotal) {
		RunMaintainMetricstotal = runMaintainMetricstotal;
	}

	public void setDone_APJ(int done_APJ) {
		Done_APJ = done_APJ;
	}

	public int getDone_USA() {
		return Done_USA;
	}

	public void setDone_USA(int done_USA) {
		Done_USA = done_USA;
	}

	public int getDone_MEXICO() {
		return Done_MEXICO;
	}

	public void setDone_MEXICO(int done_MEXICO) {
		Done_MEXICO = done_MEXICO;
	}

	public int getDone_EMEA() {
		return Done_EMEA;
	}

	public void setDone_EMEA(int done_EMEA) {
		Done_EMEA = done_EMEA;
	}

	public int getInProgress_APJ() {
		return InProgress_APJ;
	}

	public void setInProgress_APJ(int inProgress_APJ) {
		InProgress_APJ = inProgress_APJ;
	}

	public int getInProgress_USA() {
		return InProgress_USA;
	}

	public void setInProgress_USA(int inProgress_USA) {
		InProgress_USA = inProgress_USA;
	}

	public int getInProgress_MEXICO() {
		return InProgress_MEXICO;
	}

	public void setInProgress_MEXICO(int inProgress_MEXICO) {
		InProgress_MEXICO = inProgress_MEXICO;
	}

	public int getInProgress_EMEA() {
		return InProgress_EMEA;
	}

	public void setInProgress_EMEA(int inProgress_EMEA) {
		InProgress_EMEA = inProgress_EMEA;
	}

	public int getInPlanning_APJ() {
		return InPlanning_APJ;
	}

	public void setInPlanning_APJ(int inPlanning_APJ) {
		InPlanning_APJ = inPlanning_APJ;
	}

	public int getInPlanning_USA() {
		return InPlanning_USA;
	}

	public void setInPlanning_USA(int inPlanning_USA) {
		InPlanning_USA = inPlanning_USA;
	}

	public int getInPlanning_MEXICO() {
		return InPlanning_MEXICO;
	}

	public void setInPlanning_MEXICO(int inPlanning_MEXICO) {
		InPlanning_MEXICO = inPlanning_MEXICO;
	}

	public int getInPlanning_EMEA() {
		return InPlanning_EMEA;
	}

	public void setInPlanning_EMEA(int inPlanning_EMEA) {
		InPlanning_EMEA = inPlanning_EMEA;
	}

	public int getUnAssinged() {
		return unAssinged;
	}

	public void setUnAssinged(int unAssinged) {
		this.unAssinged = unAssinged;
	}
	
	public String info(){
		return Done_APJ+" "+InProgress_APJ+" "+InPlanning_APJ;
		
	}
	
}
