package com.sm.ems.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("Authentication", new Feature(true));
        featureMap.put("Authorization", new Feature(false));
        featureMap.put("Rest APIs", new Feature(true));
        featureMap.put("Swagger documentation", new Feature(true));

        featureMap.put("User", new Feature(true));
        featureMap.put("Roles", new Feature(true));
        featureMap.put("Department", new Feature(true));
        featureMap.put("Course", new Feature(true));
        featureMap.put("Lecturer", new Feature(true));
        featureMap.put("Student", new Feature(true));
        featureMap.put("Charts", new Feature(true));
        featureMap.put("Reports", new Feature(true));

    }

    @ReadOperation
    public Map<String, Feature> features() {
        return featureMap;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName) {
        return featureMap.get(featureName);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private boolean isEnabled;

    }
}
