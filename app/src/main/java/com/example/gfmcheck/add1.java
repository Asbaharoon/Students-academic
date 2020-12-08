package com.example.gfmcheck;

public class add1 {
    public String first,middle,last,email,zprn,pass,copass;

    public add1(String first,String middle,String last,String email,String zprn,String mom,String dob,String mobs,String mobp,String blood,String backlogs )
    {
        this.first=first;
        this.middle=middle;
        this.last=last;
        this.email=email;
        this.zprn=zprn;
        this.pass=pass;
        this.copass=copass;
    }
    public String getFirst(){ return first; }
    public String getMiddle(){ return middle; }
    public String getLast(){ return last; }
    public String getEmail(){ return email; }
    public String getZprn(){ return zprn; }
    public String getPass(){ return pass; }
    public String getCopass(){ return copass; }
}
