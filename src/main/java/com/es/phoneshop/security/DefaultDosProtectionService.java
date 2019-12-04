package com.es.phoneshop.security;

import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultDosProtectionService implements DosProtectionService {
    private static DosProtectionService dosProtectionService;

    private static final int MAX_REQUEST_COUNT = 10;
    private static final Long MAX_TIME = 10000L;

    private Map<String, Pair<Integer, Long>> countRequestMap = new ConcurrentHashMap<>();

    private DefaultDosProtectionService() {
    }

    public static DosProtectionService getInstance() {
        if (dosProtectionService == null) {
            dosProtectionService = new DefaultDosProtectionService();
        }
        return dosProtectionService;
    }

    @Override
    public boolean allowed(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Long time = new Date().getTime();
        Pair<Integer, Long> pair = countRequestMap.get(ip);
        if (pair == null || time - pair.getValue() > MAX_TIME) {
            countRequestMap.put(ip, new Pair<>(1, time));
            return true;
        } else {
            if (pair.getKey() > MAX_REQUEST_COUNT && time - pair.getValue() <= MAX_TIME) {
                return false;
            }
        }
        countRequestMap.put(ip, new Pair<>(pair.getKey() + 1, pair.getValue()));
        return true;
    }
}
