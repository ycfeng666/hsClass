using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace work1
{
    class People:Eat
    {
        public void SayName()
        {
            Console.WriteLine("{0}!", Name);
        }
    }
}
