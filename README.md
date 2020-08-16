###1. MyBatis
    - HelloWord
    - 
###2. MyBatis Rule for argument passing
    1. single argument ===> #{arguement}
    2. multiple arguements 
        2.1 method is: public Employee getEmpByIdAndLastName(String id,String lastName)
        2.2 get the value: #{id}, #{lastName}
        2.3 mybatis exception: Parameter id not found, Available parameters are [0,1,param1,param2]
        