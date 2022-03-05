const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=UTF-8',
        'Referer': null
    },
    getAllUsers: async () => await fetch('/api/users'),
    getUserByEmail: async () => await fetch(`/api/users`),
    getUserById: async (id) => await fetch(`api/users/` + id),
    addUser: async (user) => await fetch('/api/users',
        {method: "POST", headers: userFetch.head, body: JSON.stringify(user)}),
    updateUser: async (user) => await fetch(`/api/users/`,
        {method: 'PUT', headers: userFetch.head, body: JSON.stringify(user)}),
    deleteUserByID: async (id) => await fetch(`/api/users/` + id,
        {method: 'DELETE', headers: userFetch.head})

}



function getRoles(list) {
    let userRoles = [];
    for (let role of list) {
        if (role.role === "ROLE_ADMIN") {
            userRoles.push(" ADMIN");
        }
        if (role.role === "ROLE_USER") {
            userRoles.push(" USER");
        }

    }
    let stringRoles = userRoles.join("  ");
    return stringRoles;
}

function addRoles(role) {
    let roles = [];
    if (role === "ADMIN") {
        roles.push({id: 1});
    } else {
        roles.push({id: 2});
    }
    return roles;
}

getUsers()
function getUsers() {
    userFetch.getAllUsers().then(
        res => {
            res.json().then(
                users => {
                    users.forEach((user) => {
                        console.log(user)
                        let stringRoles = getRoles(user.roles);
                        document.querySelector('#tBody').insertAdjacentHTML('beforeend',
                            `<tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${stringRoles}</td>
                <td>
                <button type="submit" onclick="editUser(${user.id})"
                class="btn btn-info" data-toggle="modal" data-target="#editUser">Edit</button>
                </td>
                <td>
                <button type="submit" onclick="deleteUser(${user.id})"
                class="btn btn-danger" data-toggle="modal" data-target="#deleteUser">Delete</button>
                </td>
                </tr>`);
                    })
                }
            )
        }
    )
}

function addUserData() {
    document.addEventListener('DOMContentLoaded', addUserData);
    let firstName = document.getElementById('firstName').value;
    let lastName = document.getElementById('lastName').value;
    let age = document.getElementById('age').value;
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let role = document.getElementById('rolesAdd').value;
    let user = {
        firstName: firstName,
        lastName: lastName,
        age: age,
        email: email,
        password: password,
        roles: addRoles(role)
    };
    console.log(user)
    userFetch.addUser(user).then(() => {
        document.getElementById('firstName').value = ``;
        document.getElementById('lastName').value = ``;
        document.getElementById('age').value = ``;
        document.getElementById('email').value = ``;
        document.getElementById('password').value = ``;
        document.getElementById('rolesAdd').value = ``;
        document.getElementById('tableUsers').innerHTML = ``;
        getUsers();
    })
}

function editUser(id) {
    userFetch.getUserById(id)
        .then(res => {
            res.json().then(user => {
                $('#editId').val(user.id)
                $('#editFirstName').val(user.firstName)
                $('#editLastName').val(user.lastName)
                $('#editAge').val(user.age)
                $('#editEmail').val(user.email)
                $('#editPassword').val("")
                $('#editRoles').val(getRoles(user.roles))
            })
        })
}

function updateUser() {
    let id = document.getElementById('editId').value;
    let firstName = document.getElementById('editFirstName').value;
    let lastName = document.getElementById('editLastName').value;
    let age = document.getElementById('editAge').value;
    let email = document.getElementById('editEmail').value;
    let password = document.getElementById('editPassword').value;
    let role = document.getElementById('editRoles').value;
    let user = {
        id: id,
        firstName: firstName,
        lastName: lastName,
        age: age,
        email: email,
        password: password,
        roles: addRoles(role)
    };
    console.log(user)
    console.log(JSON.stringify(user))
    userFetch.updateUser(user).then(() => {
        document.getElementById('editId').value = ``;
        document.getElementById('editFirstName').value = ``;
        document.getElementById('editLastName').value = ``;
        document.getElementById('editAge').value = ``;
        document.getElementById('editEmail').value = ``;
        document.getElementById('editPassword').value = ``;
        document.getElementById('editRoles').value = ``;
        document.getElementById('tableUsers').innerHTML = ``;
        getUsers();
        $('#editUser').modal('hide');

    })
}

function deleteUser(id) {
    userFetch.getUserById(id)
        .then(res => {
            res.json().then(user => {
                $('#deleteId').val(user.id)
                $('#deleteFirstName').val(user.firstName)
                $('#deleteLastName').val(user.lastName)
                $('#deleteAge').val(user.age)
                $('#deleteEmail').val(user.email)
                $('#deleteRoles').val(getRoles(user.roles))
            })
        })
}

function deleteUserById() {
    let id = document.getElementById('deleteId').value;
    userFetch.deleteUserByID(id).then(() => {
        document.getElementById('deleteId').value = ``;
        document.getElementById('deleteFirstName').value = ``;
        document.getElementById('deleteLastName').value = ``;
        document.getElementById('deleteAge').value = ``;
        document.getElementById('deleteEmail').value = ``;
        document.getElementById('deleteRoles').value = ``;
        document.getElementById('tableUsers').innerHTML = ``;
        getUsers();
        $('#deleteUser').modal('hide');
    });
}

infoUser()
function infoUser() {
    userFetch.getUserByEmail()
        .then(res => res.json())
        .then(user => {
            let stringRoles = getRoles(user.roles);
            document.querySelector('#infoUser').innerHTML = `
                ${user.email} with roles: ${stringRoles}
            `;
            document.querySelector('#userById').innerHTML = `
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${stringRoles}</td>
                </tr>
            `;
        });
}


