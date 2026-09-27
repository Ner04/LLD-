class UserService{
    private UserValidator validator;
    private UserRepository repository;
    private UserEmailService emailService;

    public registerUser(String name, String email){
        validator.validate(name, email);
        repository.save(name, email);
    }
}




class UserValidator{
    public void validate(String name, String email){
        // validation logic
    }
}


class UserRepository{
    public void save(String name, String email){
        // Database logic
    }
}

class UserEmailService{
    public void sendWelcomeEmail(String email){
        // Email sending logic
    }
}