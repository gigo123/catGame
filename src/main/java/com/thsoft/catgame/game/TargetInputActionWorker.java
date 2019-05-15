package com.thsoft.catgame.game;

import com.thsoft.catgame.gameLogik.LaunchPadS;;
public class TargetInputActionWorker  implements InputActionWorker{
	private LaunchPadS lauchPAd;

	
	public TargetInputActionWorker(LaunchPadS lauchPAd) {
		super();
		this.lauchPAd = lauchPAd;
	}
	
	public void setLauchPAd(LaunchPadS lauchPAd) {
		this.lauchPAd = lauchPAd;
	}

	public void LeftKey() {
			lauchPAd.downSpeead();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
	}

	public void RightKey() {
		
			lauchPAd.addSpeead();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
	}

	public void UpKey() {
			lauchPAd.upAngle();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
	}

	public void DownKey() {	
			lauchPAd.downAngle();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
	}
}
