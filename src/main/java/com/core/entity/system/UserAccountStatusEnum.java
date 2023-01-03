package com.core.entity.system;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;

@AllArgsConstructor
@Getter
public enum UserAccountStatusEnum {
    ACTIVE("Active",100),
    INACTIVE("Inactive",101),
    DELETED("Deleted",102);

    private final String key;
    private final Integer value;

    public static  String getKeyOf(Integer value){
        String key= "";
        if(Objects.equals(value, ACTIVE.value)){
            key = ACTIVE.key;
        } else if(Objects.equals(value, INACTIVE.value)){
            key = INACTIVE.key;
        }else if(Objects.equals(value, DELETED.value)){
            key = DELETED.key;
        }
        return key;
    }

    public static Map<String,Integer> getAll(){
        Map<String,Integer> map = new HashMap<>();
        map.put(ACTIVE.key, ACTIVE.value);
        map.put(INACTIVE.key, INACTIVE.value);
        map.put(DELETED.key, DELETED.value);
        return SortByValue(map);
    }

    public static Map<String, Integer> SortByValue(Map<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =new LinkedList<>(hm.entrySet());
        // Sort the list
        list.sort((o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));
        // put data from sorted list to hashmap
        HashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            result.put(aa.getKey(), aa.getValue());
        }
        return result;
    }
}
