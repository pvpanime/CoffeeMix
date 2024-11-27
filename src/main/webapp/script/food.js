
const dialog = document.getElementById("the-modal-dialog");
const foodIdInput = document.getElementById("food-id")
const foodNameInput = document.getElementById("food-name")
const foodPriceInput = document.getElementById("food-price")
const foodDescInput = document.getElementById("food-desc")
let mode = null;

function openDialog(id, name, price, description) {
    foodIdInput.value = id;
    foodNameInput.value = name;
    foodPriceInput.value = price;
    foodDescInput.value = description;
    mode = 'update'
    dialog.showModal();
}

function openDialogUsing(id) {
    fetch('/food/get/' + id).then(res => {
        if (res.ok) return res.json();
        else throw new Error(res.statusText);
    }).then(json => {
        const {id, name, price, description, status} = json
        openDialog(id, name, price, description);
    }).catch(err => {
        console.error(err);
    })
}

function openDialogForNew() {
    foodIdInput.value = '';
    foodNameInput.value = '';
    foodPriceInput.value = '';
    foodDescInput.value = '';
    mode = 'insert'
    dialog.showModal();
}

function requestUpdate() {
    const body = new URLSearchParams({
        id: foodIdInput.value,
        name: foodNameInput.value,
        price: foodPriceInput.value,
        description: foodDescInput.value
    });
    fetch('/food/update', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: body,
    }).then(response => (response.ok) && location.reload())
}

function requestInsert() {
    const body = new URLSearchParams({
        name: foodNameInput.value,
        price: foodPriceInput.value,
        description: foodDescInput.value
    });
    fetch('/food/insert', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: body,
    }).then(response => (response.ok) && location.reload())
}

function doSubmit() {
    if (mode === 'update') requestUpdate()
    else if (mode === 'insert') requestInsert()
}

function requestUpdateStatus(id, status) {
    fetch('/food/update/status', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ id, status })
    }).then(response => {
        if (response.ok) location.reload()
        else throw new Error(response.statusText)
    }).catch(err => {
        console.error(err);
    })
}

function requestDelete(id) {
    fetch(`/food/delete`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ id })
    }).then(response => {
        if (response.ok) location.reload()
        else throw new Error(response.statusText)
    }).catch(err => {
        console.error(err);
    })
}

document.querySelectorAll('#main-table tbody tr').forEach(tr => {
    const id = tr.getAttribute('data-id');
    tr.addEventListener('click', () => {
        if (id != null) openDialogUsing(id);
    })
    tr.querySelector("input[type='checkbox']").addEventListener('click', checkEvent => {
        checkEvent.preventDefault();
        checkEvent.stopPropagation();
        const checkbox = checkEvent.currentTarget;

        if (checkbox.checked) {
            requestUpdateStatus(id, 1)
        } else {
            requestUpdateStatus(id, 0)
        }
        return false
    })
    tr.querySelector("button.btn-danger").addEventListener('click', () => {
        requestDelete(id)
    })
})
document.getElementById('submit-button').addEventListener('click', doSubmit)
document.getElementById('cancel-button').addEventListener('click', () => dialog.close());
