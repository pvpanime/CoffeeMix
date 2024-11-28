<button id="logoutButton" class="col btn btn-danger">Logout</button>
<script>
  document.querySelector('#logoutButton').addEventListener('click', async () => {
      const response = await fetch('/api/logout', { method: 'POST' })
      if (response.ok) {
          window.location.reload()
      }
  })
</script>