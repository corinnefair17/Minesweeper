import argparse
import subprocess
import sys
import os

parser = argparse.ArgumentParser()
parser.add_argument("assignment")
parser.add_argument("files", default=[], nargs='*')

args   = parser.parse_args()

target_url="https://web-cat.cs.vt.edu/Web-CAT/WebObjects/Web-CAT.woa/wa/assignments/eclipse?institution=ChristopherNewport"
submitter ="webcat-submitter-1.0.4.jar"
login_info="login.txt"

valid_files = []
invalid_files=[]

for f in args.files:
	if not f in os.listdir('./src'):
		print('WARNING: I couldn\'t find ' + f)
		invalid_files.append(f)
	else:
		valid_files.append(f)

with open(login_info) as creds:
	temp = []
	for line in creds:
		temp.append(line.strip())
	# Only get the first two elements to skip any blank lines that students keep adding to end
	username = temp[0]
	password = temp[1]

runme = ['java', '-jar', submitter, '-t', target_url, '-u', username, '-p', password, '-a', args.assignment]
for f in valid_files:
	runme.append('./src/'+f.strip())

o = ''
try:
	o = subprocess.run(runme, check=True, stdout=subprocess.PIPE).stdout.strip()
except subprocess.CalledProcessError as e:
	print('Something went wrong.')
	print(e.output)
	sys.exit(1)

if 'Your login information could not be validated' in str(o):
	sys.exit('Your login information is incorrect')
else:
	if (len(invalid_files)):
		print(" Submitted without some files:")
		for f in invalid_files:
			print("   "+f)

		sys.exit(2)
	else:
		print('Submission Success!')
