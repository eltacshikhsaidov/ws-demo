package io.shikhsaidov.wsdemo.response;

import io.shikhsaidov.wsdemo.enums.Action;
import io.shikhsaidov.wsdemo.enums.State;
import io.shikhsaidov.wsdemo.enums.Type;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeActivityResponse {
    Type type;
    Action action;
    State state;
    Long docNo;
}
