import java.sql.SQLIntegrityConstraintViolationException;import java.util.List;
class RegistrationService {
 private final RegistrationDao dao=new RegistrationDao();
 void register(String id,String name,String event)throws Exception{if(id==null||!id.trim().matches("[A-Za-z0-9-]{3,24}")||name==null||name.trim().isEmpty()||event==null||event.isBlank())throw new IllegalArgumentException("Enter a valid student ID, name and event.");try{dao.insert(id.trim(),name.trim(),event);}catch(SQLIntegrityConstraintViolationException ex){throw new DuplicateRegistrationException("This student is already registered for that event.");}}
 List<Object[]> page(String event,int limit,int offset)throws Exception{return dao.page(event,limit,offset);}
}
class DuplicateRegistrationException extends Exception {DuplicateRegistrationException(String message){super(message);}}
