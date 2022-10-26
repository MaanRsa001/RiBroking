<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

    <script>
      tinymce.init({
        selector: '#mytextarea',
        plugins: [
          'a11ychecker','advlist','advcode','advtable','autolink','checklist','export',
          'lists','link','image','charmap','preview','anchor','searchreplace','visualblocks',
          'powerpaste','fullscreen','formatpainter','insertdatetime','media','table','help','wordcount'
        ],
        toolbar: 'undo redo | formatpainter casechange blocks | bold italic backcolor | ' +
          'alignleft aligncenter alignright alignjustify | ' +
          'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help'
      });
    </script>

  </head>

  <body>
    <form method="post">
    	<div class="form-row mb-3">
              <label for="to" class="col-2 col-sm-1 col-form-label">To:</label>
              <div class="col-10 col-sm-11">
                  <input type="email" class="form-control" id="to" placeholder="Type email">
              </div>
          </div>
          <div class="form-row mb-3">
              <label for="cc" class="col-2 col-sm-1 col-form-label">CC:</label>
              <div class="col-10 col-sm-11">
                  <input type="email" class="form-control" id="cc" placeholder="Type email">
              </div>
          </div>		
      <textarea id="mytextarea">Hello, World!</textarea>
    </form>
  </body>
</html>