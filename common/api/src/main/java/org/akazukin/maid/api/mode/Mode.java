package org.akazukin.maid.api.mode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Mode {
    JOB(0),
    WAIT(1),
    FREE(2);

    int id;

    public static Mode fromId(final int id) {
        for (final Mode mode : values()) {
            if (mode.getId() == id) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Unknown mode id: " + id);
    }


    public Mode getNext() {
        final Mode[] values = values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
