package com.isl.gbm.rbibond.service.impl;
import com.isl.gbm.Utils.LockInfo;
import com.isl.gbm.rbibond.service.RecordLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RecordLockServiceImpl implements RecordLockService {
    private static final Logger log = LoggerFactory.getLogger(RecordLockServiceImpl.class);

    private final Map<Integer, LockInfo> lockedRecords = new ConcurrentHashMap<>();
    private static final long LOCK_EXPIRY_MINUTES = 20; // auto remove after 20 mins

    // Try to lock a record
    public boolean lockRecord(int recordId, String pan) {
        if (!lockedRecords.containsKey(recordId)) {
            lockedRecords.computeIfAbsent(
                    recordId,
                    id -> new LockInfo(pan, LocalDateTime.now())
            );
            log.info("Record locked successfully | recordId={} | pan={} | time={}",
                    recordId, maskPan(pan), LocalDateTime.now());
            return true;
        }
        log.info("Record Already locked | recordId={} | pan={} | time={}",
                recordId, maskPan(pan), LocalDateTime.now());
        return false;
    }
    public boolean unlockByPan(String pan) {
        if (pan == null || pan.trim().isEmpty()) {
            return false;
        }
        log.info("Record Unlock based on pan | pan={} | time={}",maskPan(pan), LocalDateTime.now());
        return lockedRecords.entrySet()
                .removeIf(entry -> pan.equalsIgnoreCase(entry.getValue().getPan()));
    }
    // Unlock a record
    public synchronized void unlockByRecordId(int recordId) {
        log.info("Record remove based on recordId | recordId={} | time={}",recordId, LocalDateTime.now());
        lockedRecords.remove(recordId);
    }

    // Check if record is locked
    public boolean isLocked(int recordId) {
        log.info("Record isLocked based on recordId | recordId={} | time={}",recordId, LocalDateTime.now());
        return lockedRecords.containsKey(recordId);
    }
    private static String maskPan(String pan) {
        if (pan == null || pan.length() != 10) {
            return pan;
        }
        // Keep first 2 and last 3 visible
        return pan.substring(0, 2) + "xxxx" + pan.substring(6);
    }
    @Scheduled(fixedRate = LOCK_EXPIRY_MINUTES * 60 * 1000)
    public void clearAllLockedRecords() {
        int sizeBefore = lockedRecords.size();
        if (sizeBefore > 0) {
            lockedRecords.clear();
            log.info("Auto cleared lockedRecords | clearedCount={} | expiryMinutes={} | time={}",sizeBefore,LOCK_EXPIRY_MINUTES,LocalDateTime.now()
            );
        }
    }
}
