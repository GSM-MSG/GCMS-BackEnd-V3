package com.gcms.v3.global.common.ulid;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.time.Instant;

public class ULIDGenerator implements IdentifierGenerator {

    private static final SecureRandom random = new SecureRandom();

    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) {
        return generateULID();
    }

    public static byte[] generateULID() {
        long milliseconds = Instant.now().toEpochMilli();

        byte[] bytes = new byte[16];
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        buffer.putLong(milliseconds);
        buffer.putLong(random.nextLong());

        return bytes;
    }

    private static String toULIDString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (i == 6 || i == 8 || i == 10 || i == 12) {
                sb.append('-');
            }
            int value = bytes[i] & 0xFF;
            sb.append(Integer.toHexString(value >>> 4));
            sb.append(Integer.toHexString(value & 0x0F));
        }
        return sb.toString();
    }
}
