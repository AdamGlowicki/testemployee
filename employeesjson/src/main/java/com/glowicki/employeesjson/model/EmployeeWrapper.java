package com.glowicki.employeesjson.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class EmployeeWrapper {

    @SerializedName("employees")
    @Expose
    private List<Employee> employees = null;

}
