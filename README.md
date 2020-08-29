###1. MyBatis
    - HelloWord
    - 
###2. MyBatis Rule for argument passing
    1. single argument ===> #{arguement}
    2. multiple arguements 
        2.1 method is: public Employee getEmpByIdAndLastName(String id,String lastName)
        2.2 get the value: #{id}, #{lastName}
        2.3 mybatis exception: Parameter id not found, Available parameters are [0,1,param1,param2]
        2.4 solution: use @Param("id") annotation
    3. POJO: use pojo variable name directly
    4. Map: use key value pair for argument value extraction
    5. If used frequently, we can write a TO(Transfer Object)

###3. Example
    1. public Employee getEmpByIdAndLastName(@Param("id") String id, String lastName)
        id===>#{id} or #{param1}
        lastName===>#{param2}
    2. public Employee getEmp(Integer id, Emp emp)
        id===>#{param1}
        lastName===>#{param2.lastName}
    3. public Employee getEmp(List<Interger> ids)
        id(the first id)===>#{list[0]}