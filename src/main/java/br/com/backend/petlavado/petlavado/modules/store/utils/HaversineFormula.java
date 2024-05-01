package br.com.backend.petlavado.petlavado.modules.store.utils;

public class HaversineFormula {
    double calculateDistance(String userGeolocation, String offerGeolocation){
        final double earthRadius = 6378137;

        // Helper function to convert degrees to radians
        double rad = Math.PI / 180.0;

        // Splitting coordinates into latitude and longitude components
        String[] point1 = userGeolocation.split(",");
        String[] point2 = offerGeolocation.split(",");
        
        // Converting latitude and longitude components to radians
        double lat1 = Double.parseDouble(point1[0]) * rad;
        double lon1 = Double.parseDouble(point1[1]) * rad;
        double lat2 = Double.parseDouble(point2[0]) * rad;
        double lon2 = Double.parseDouble(point2[1]) * rad;

        // Differences in latitude and longitude
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = earthRadius * c; // distance in meters

        // Converting distance from meters to kilometers and rounding to two decimal places
        return Math.round((d / 1000) * 100.0) / 100.0;
    }

}
