package br.com.impacta.curso.exercicios;

import java.util.ArrayList;
import java.util.List;




class User {

    User() {
        _phones = new ArrayList<>();
        _emails = new ArrayList<>();
    }

    private List<Phone> _phones;

    private List<Email> _emails;

    void addPhone(Phone phone) {
        _phones.add(phone);
    }

    void addEmail(Email email) {
        _emails.add(email);
    }


   Email getFirstEmailByType(String type) {
       for (Email email:_emails) {
           if (email.getType().equals(type))
           {
               return email;
           }
       }
       return null;
   }

    Phone getFirstPhoneByType(String type) {
        for (Phone phone:_phones) {
            if (phone.getType().equals(type))
            {
                return phone;
            }
        }
        return null;
    }

}
