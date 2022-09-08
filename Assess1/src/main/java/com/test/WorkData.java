package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkData {
    public Statement getStatement() {
        Connection con = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/workers", "root", "password");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public int SaveWorker(Worker wk){
        try{
            String sql = "insert into worker values ("+wk.getId()+",'"+wk.getFirst_name()+"','"+wk.getLast_name()+"',"+wk.getSalary()+",'"+wk.getJoining_date()+"','"+wk.getDepartment()+"')";
            return getStatement().executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Worker wk){
        try{
            String sql = "update worker set first_name = '"+wk.getFirst_name()+"', last_name = '"+wk.getLast_name()+"', salary = "+wk.getSalary()+" ,joining_date = '"+wk.getJoining_date()+"',department = '"+wk.getDepartment()+"' where id = "+wk.getId()+"";
            return getStatement().executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(Worker wk){
        try{
            String sql = "delete from worker where id = "+wk.getId()+"";
            return getStatement().executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public List<String> query1(Worker wk){
        List<String> full_name = new ArrayList<>();
        try{
            String sql = "select concat(UPPER(first_name),' ',UPPER(last_name)) as fullname from worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                full_name.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return full_name;
    }

    public List<String> query2(Worker wk){
        List<String> distDept = new ArrayList<>();
        try{
            String sql = "select distinct(department) from worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                distDept.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distDept;
    }

    public void query3(Worker wk){
        String sql = null;
        try{
            sql = "select position('a' in first_name) from worker where id=4;";
            ResultSet rs = getStatement().executeQuery(sql);
            if(rs.next())
                System.out.println(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getAll(){
        List<Worker> lst = new ArrayList<Worker>();
        try{
            String sql = "select * from worker";
            ResultSet rs = getStatement().executeQuery(sql);
            while(rs.next()){
                Worker wk =  new Worker();
                wk.setId(rs.getInt(1));
                wk.setFirst_name(rs.getString(2));
                wk.setLast_name(rs.getString(3));
                wk.setSalary(rs.getInt(4));
                wk.setJoining_date(rs.getString(5));
                wk.setDepartment(rs.getString(6));
                lst.add(wk);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lst;
    }
}
