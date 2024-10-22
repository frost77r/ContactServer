package contactmanager.ui;

import contactmanager.service.ContactService;
import contactmanager.service.impl.ContactServiceImpl;
import contactmanager.model.Contact;

import java.util.Scanner;

public class ContactManagerUI {
    private ContactService contactService = new ContactServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("个人通讯录管理系统");
            System.out.println("1. 添加联系人");
            System.out.println("2. 修改联系人");
            System.out.println("3. 删除联系人");
            System.out.println("4. 查看联系人");
            System.out.println("5. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    updateContact();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    viewContacts();
                    break;
                case 5:
                    System.out.println("退出系统。");
                    return;
                default:
                    System.out.println("无效选择，请重新输入。");
            }
        }
    }

    private void addContact() {
        System.out.print("请输入联系人姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入联系人电话：");
        String phone = scanner.nextLine();
        System.out.print("请输入联系人邮箱：");
        String email = scanner.nextLine();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);

        contactService.addContact(contact);
        System.out.println("联系人添加成功！");
    }

    private void updateContact() {
        System.out.print("请输入要修改的联系人 ID：");
        int id = scanner.nextInt();
        scanner.nextLine();

        Contact existingContact = contactService.getContactById(id);
        if (existingContact == null) {
            System.out.println("联系人不存在！");
            return;
        }

        System.out.print("请输入新的联系人姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入新的联系人电话：");
        String phone = scanner.nextLine();
        System.out.print("请输入新的联系人邮箱：");
        String email = scanner.nextLine();

        existingContact.setName(name);
        existingContact.setPhone(phone);
        existingContact.setEmail(email);

        contactService.updateContact(existingContact);
        System.out.println("联系人修改成功！");
    }

    private void deleteContact() {
        System.out.print("请输入要删除的联系人 ID：");
        int id = scanner.nextInt();
        scanner.nextLine();

        contactService.deleteContact(id);
        System.out.println("联系人删除成功！");
    }

    private void viewContacts() {
        System.out.println("联系人列表：");
        contactService.getAllContacts().forEach(contact ->
                System.out.println("ID: " + contact.getId() + ", 姓名: " + contact.getName() + ", 电话: " + contact.getPhone() + ", 邮箱: " + contact.getEmail())
        );
    }
}
