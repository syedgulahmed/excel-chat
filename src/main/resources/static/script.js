const output = document.getElementById('output');
const input = document.getElementById('input');
const fileInput = document.getElementById('fileInput');

function addMessage(who, text) {
  const div = document.createElement('div');
  div.className = 'msg ' + who;
  div.textContent = (who === 'system' ? '' : who + ': ') + text;
  output.appendChild(div);
  output.scrollTop = output.scrollHeight;
}

async function upload() {
  const file = fileInput.files[0];
  if (!file) {
    addMessage('error', 'Please choose a file first');
    return;
  }

  addMessage('system', `Uploading "${file.name}"...`);

  const formData = new FormData();
  formData.append('file', file);

  try {
    const response = await fetch('/upload', {
      method: 'POST',
      body: formData
    });

    if (!response.ok) {
      addMessage('error', `Server returned ${response.status}`);
      return;
    }

    const text = await response.text();
    addMessage('system', `Received content from "${file.name}":`);
    addMessage('bot', text);
    fileInput.value = '';
  } catch (err) {
    addMessage('error', 'Upload failed: ' + err.message);
  }
}

async function send() {
  const message = input.value.trim();
  if (!message) return;

  addMessage('user', message);
  input.value = '';

  try {
    const response = await fetch('/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'text/plain' },
      body: message
    });

    if (!response.ok) {
      addMessage('error', `Server returned ${response.status}`);
      return;
    }

    const reply = await response.text();
    addMessage('bot', reply);
  } catch (err) {
    addMessage('error', 'Request failed: ' + err.message);
  }
}