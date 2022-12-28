package com.core.entity.system;

public enum UserAccountStatusEnum {

        INACTIVE((int) 101), ACTIVE((int) 100), DELETE((int) 2);
        public final int value;

        private UserAccountStatusEnum(int value) {
            this.value = value;
        }

}
