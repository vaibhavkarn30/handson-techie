package org.handsontechie.rocksDBService;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;

public class RocksDbMain {

    static {
        RocksDB.loadLibrary();  // Load the RocksDB C++ library
    }
    public static void main(String[] args) {
        System.out.println("RocksDb Main Started");
        String dbPath = "/Users/vkarn/Documents/handsOnTechie/rocksDbData";
        try(final Options options = new Options().setCreateIfMissing(true)) {

            RocksDB db = RocksDB.open(options,dbPath);
            db.put("vaibhav".getBytes(), "se2".getBytes());
            db.put("deepak".getBytes(), "lead".getBytes());

            byte[] value = db.get("vaibhav".getBytes());
            if (value != null) {
                System.out.println("Retrieved value for vaibhav " + new String(value));
            }

        } catch (Exception ex) {
            System.out.println("Exception occurred while connecting or initialising with rocks db" + ex);
        }
    }
}
