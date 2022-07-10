package Pecadillo.isika.al.payload.request;



public class MeteoPreferenceRequest {
	
		private Long id;
		
		private double tempMin;
		
		private double tempMax;
		
		private double ventMin;
		
		private double ventMax;
		
		private double pluieMin;
		
		private double pluieMax;
		
		private double latitude;
		
		private double longitude;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getTempMin() {
			return tempMin;
		}

		public void setTempMin(double tempMin) {
			this.tempMin = tempMin;
		}

		public double getTempMax() {
			return tempMax;
		}

		public void setTempMax(double tempMax) {
			this.tempMax = tempMax;
		}

		public double getVentMin() {
			return ventMin;
		}

		public void setVentMin(double ventMin) {
			this.ventMin = ventMin;
		}

		public double getVentMax() {
			return ventMax;
		}

		public void setVentMax(double ventMax) {
			this.ventMax = ventMax;
		}

		public double getPluieMin() {
			return pluieMin;
		}

		public void setPluieMin(double pluieMin) {
			this.pluieMin = pluieMin;
		}

		public double getPluieMax() {
			return pluieMax;
		}

		public void setPluieMax(double pluieMax) {
			this.pluieMax = pluieMax;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		@Override
		public String toString() {
			return "MeteoPreferenceRequest [id=" + id + ", tempMin=" + tempMin + ", tempMax=" + tempMax + ", ventMin="
					+ ventMin + ", ventMax=" + ventMax + ", pluieMin=" + pluieMin + ", pluieMax=" + pluieMax
					+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
		}
		
		
		

}
