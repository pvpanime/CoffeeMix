const viewDialog = document.getElementById('view-only-dialog');
const editDialog = document.getElementById("edit-dialog");
const foodIdInput = document.getElementById("food-input-id")
const foodNameInput = document.getElementById("food-input-name")
const foodPriceInput = document.getElementById("food-input-price")
const foodDescInput = document.getElementById("food-input-desc")
const foodNameView = document.getElementById("food-view-name");
const foodPriceView = document.getElementById("food-view-price");
const foodDescView = document.getElementById("food-view-desc");
let mode = null;

async function getFood(id) {
  const res = await fetch('/food/get/' + id);
  if (res.ok) {
    return await res.json();
  } else {
    throw new Error(res.statusText);
  }
}

async function openFoodView (_id) {
  try {
    const { id, name, price, description, } = await getFood(_id)
    foodNameView.innerText = name
    foodPriceView.innerText = price
    foodDescView.innerText = description
    viewDialog.showModal();
  } catch (e) {
    console.error(e);
  }
}

function openFoodEdit(id = '', name = '', price = '', description = '') {
  foodIdInput.value = id;
  foodNameInput.value = name;
  foodPriceInput.value = price;
  foodDescInput.value = description;
  mode = id ? 'update' : 'insert';
  editDialog.showModal();
}


async function openFoodEditUsing(_id) {
  try {
    const { id, name, price, description, } = await getFood(_id)
    openFoodEdit(id, name, price, description);
  } catch (e) {
    console.error(e);
  }
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
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
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
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
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
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    body: new URLSearchParams({id, status})
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
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    body: new URLSearchParams({id})
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
    // if (id != null) openFoodEditUsing(id);
    if (id != null) openFoodView(id);
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
  // tr.querySelector("button.btn-danger").addEventListener('click', () => {
  //     requestDelete(id)
  // })
})
document.getElementById('submit-button').addEventListener('click', doSubmit)
document.getElementById('cancel-button').addEventListener('click', () => editDialog.close());
