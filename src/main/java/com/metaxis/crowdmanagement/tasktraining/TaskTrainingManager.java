/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.tasktraining;

import com.metaxis.crowdmanagement.exceptions.CrowdExceptionCodes;
import com.metaxis.crowdmanagement.exceptions.CrowdExceptions;
import com.metaxis.crowdmanagement.response.ResponseError;
import com.metaxis.crowdmanagement.tasktraining.pojo.TrainingRecord;
import com.metaxis.crowdmanagement.utils.Manager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAnstee
 */
public class TaskTrainingManager extends Manager {
    
    public TaskTrainingManager()
    {
        super();
    }
    
    public TrainingRecord getExampleRecord(String type)
    {
        TrainingRecord record = new TrainingRecord();
        
        try {
            ps1 = dbCalls.getCrowdCon().prepareStatement("EXEC grabExampleTrainingRecord ?",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ps1.setString(1, type);
            ResultSet rs = ps1.executeQuery();

            if (rs != null && rs.next()) {
                record.setRecordid(rs.getLong("RecordID"));
                record.setTaskid(rs.getInt("TaskID"));
                record.setRecordtype(rs.getString("RecordType"));                
                record.setPagehtml(rs.getString("PageHTML"));
                record.setResponseshtml(rs.getString("ResponseHTML"));
            }
            return record;                  
        } catch (SQLException ex) {
            Logger.getLogger(TaskTrainingManager.class.getName()).log(Level.SEVERE, null, ex);
            setErrorLog(ex);
            error = new ResponseError(CrowdExceptionCodes.EXAMPLE_TRAINING_RECORD_ERROR,
                    CrowdExceptions.getException("training_example_error"),
                    CrowdExceptions.getException("training_header"));
        }        
        return null;
    }
}
