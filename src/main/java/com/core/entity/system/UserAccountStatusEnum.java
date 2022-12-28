package com.core.entity.system;

public enum UserAccountStatusEnum {

        INACTIVE((int) 0), ACTIVE((int) 1), DELETE((int) 2);
        public final int value;

        private UserAccountStatusEnum(int value) {
            this.value = value;
        }

}
