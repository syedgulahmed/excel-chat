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

async function analyze() {
  const message = input.value.trim();
  const file = fileInput.files[0];

  if (!message && !file) {
    addMessage('error', 'Please enter a message or choose a file');
    return;
  }

  // Show user's input in chat
  if (message) addMessage('user', message);
  if (file) addMessage('system', `Uploading "${file.name}"...`);

  // Build the request
  const formData = new FormData();
  if (message) formData.append('prompt', message);
  if (file) formData.append('file', file);

  // Clear inputs immediately
  input.value = '';
  fileInput.value = '';

  try {
    const response = await fetch('/api/analyze', {
      method: 'POST',
      body: formData
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